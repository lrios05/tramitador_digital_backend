package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.mapper.ContractMapper;
import com.firmadigital.tramitador.dto.mapper.CustomerMapper;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.*;
import com.firmadigital.tramitador.model.customer.Customer;
import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import com.firmadigital.tramitador.repository.contract.*;
import com.firmadigital.tramitador.repository.customer.CustomerRepository;
import com.firmadigital.tramitador.repository.serviceoffer.ServiceOfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.YearMonth;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CONTRACT;
import static com.firmadigital.tramitador.exception.EntityType.CUSTOMER;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;
import static com.firmadigital.tramitador.exception.ExceptionType.NO_DATA_FOUND;

@Component
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private PaymentFrequencyRepository paymentFrequencyRepository;
    @Autowired
    private GatherFrequencyRepository gatherFrequencyRepository;
    @Autowired
    private WasteTypeRepository wasteTypeRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private ServiceOfferRepository serviceOfferRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ContractDto findContractById(Long contractId) {
        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            return modelMapper.map(contract.get(), ContractDto.class);
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contrato ID: " + contractId);
    }

    @Override
    public ContractDto findContractByContractCode(String contractCode) {
        Optional<Contract> contract = Optional.ofNullable(
                contractRepository.findContractByContractCode(contractCode)
        );

        if (contract.isPresent()) {
            return modelMapper.map(contract.get(), ContractDto.class);
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contrato: " + contractCode);
    }

    @Override
    public ContractDto createContract(ContractDto contractDto, Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {

            Optional<ServiceOffer> serviceOffer = serviceOfferRepository
                    .findById(contractDto.getServiceOfferDto().getServiceId());

            Optional<PaymentType> paymentType = paymentTypeRepository
                    .findById(contractDto.getPaymentTypeDto().getTypeId());

            Optional<PaymentFrequency> paymentFrequency = paymentFrequencyRepository
                    .findById(contractDto.getPaymentFrequencyDto().getPFrequencyId());

            Optional<GatherFrequency> gatherFrequency = gatherFrequencyRepository
                    .findById(contractDto.getGatherFrequencyDto().getGFrequencyId());

            Optional<WasteType> wasteType = wasteTypeRepository
                    .findById(contractDto.getWasteTypeDto().getWasteId());

            Optional<Unit> unit = unitRepository.findById(contractDto.getUnitDto().getUniteId());

            Contract contract = new Contract()
                    .setCustomer(customer.get())
                    .setContractCode(getNewCode())
                    .setInitDate(contractDto.getInitDate())
                    .setEndDate(contractDto.getEndDate())
                    .setServiceOffer(serviceOffer.get())
                    .setTotalCost(contractDto.getTotalCost())
                    .setPaymentType(paymentType.get())
                    .setPaymentFrequency(paymentFrequency.get())
                    .setPayments(contractDto.getPayments())
                    .setAmount(contractDto.getAmount())
                    .setGatherFrequency(gatherFrequency.get())
                    .setWasteType(wasteType.get())
                    .setVolume(contractDto.getVolume())
                    .setUnit(unit.get())
                    .setDays(contractDto.getDays());

            return ContractMapper.toContractDto(contractRepository.save(contract));

        }

        throw exception(CUSTOMER, ENTITY_NOT_FOUND, "Customer ID" + customerId);
    }

    private String getNewCode(){
        Long quantity = contractRepository.count() + 1;
        int year = YearMonth.now().getYear();
        String newCode = quantity + "-" + year;

        return newCode;
    }

    @Override
    public ContractDto updateContract(ContractDto contractDto) {
        //TODO
        return null;
    }

    @Override
    public Page<ContractDto> findAllContracts(Pageable pageable) {

        Page<Contract> contractPage = contractRepository.findAll(pageable);

        if (contractPage.hasContent()) {
            Page<ContractDto> contractDtoPage = contractPage.map(contract -> {
                return ContractMapper.toContractDto(contract);
            });

            return contractDtoPage;
        }
        throw exception(CONTRACT, NO_DATA_FOUND, "No existen registros");
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
