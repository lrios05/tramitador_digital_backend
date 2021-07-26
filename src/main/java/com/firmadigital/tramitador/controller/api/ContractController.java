package com.firmadigital.tramitador.controller.api;


import com.firmadigital.tramitador.controller.request.ContractSignupRequest;
import com.firmadigital.tramitador.dto.model.contract.*;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.*;
import com.firmadigital.tramitador.service.customer.CustomerService;
import com.firmadigital.tramitador.service.serviceoffer.ServiceOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServiceOfferService serviceOfferService;
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private PaymentFrequencyService paymentFrequencyService;
    @Autowired
    private GatherFrequencyService gatherFrequencyService;
    @Autowired
    private WasteTypeService wasteTypeService;
    @Autowired
    private UnitService unitService;

    @GetMapping("/find/{id}")
    public Response findContract(@PathVariable ("id") Long contractId) {
        return  Response.ok().setPayload(contractService.findContractById(contractId));
    }

    @GetMapping("/findinfo/{id}")
    public Response findContractInfo(@PathVariable ("id") Long contractId) {
        return  Response.ok().setPayload(contractService.findContractInfoById(contractId));
    }

    @GetMapping("/find")
    public Response findContractByCodeAndStatus(@RequestParam(required = false) String code,
                                                @RequestParam(required = false) String status) {
        List<ContractDto> contractDtoList = new ArrayList<>();
        if (code == null || code.isEmpty()) {
            return Response.ok().setPayload(contractService.findAllByStatus(status));
        } else if (status == null || status.isEmpty()) {
            //return Response.ok().setPayload(contractService.findByContractCode(code));
            contractDtoList.add(contractService.findByContractCode(code));
            return Response.ok().setPayload(contractDtoList);
        }
        contractDtoList.add(contractService.findByCodeAndStatus(code, status));
        return Response.ok().setPayload(contractDtoList);
    }

    @GetMapping("/list")
    public Response getAllContracts(Pageable pageable){
        return Response.ok().setPayload(contractService.findAllContracts(pageable));
    }

    @PostMapping("/signup/{id}")
    public Response createContract(@PathVariable("id") Long customerId,
                                   @RequestBody @Valid ContractSignupRequest contractSignupRequest) {
        System.out.println("Codigo Cliente: " + customerId);
        return Response.ok().setPayload(registerContract(contractSignupRequest, customerId));
    }

    private ContractDto registerContract(ContractSignupRequest contractSignupRequest,
                                         Long customerId) {

        CustomerDto customerDto = customerService.findById(customerId);
        ServiceOfferDto serviceOfferDto = serviceOfferService
                .findById(contractSignupRequest.getServiceId());
        PaymentTypeDto paymentTypeDto = paymentTypeService
                .findPaymentTypeById(contractSignupRequest.getPayTypeId());
        PaymentFrequencyDto paymentFrequencyDto = paymentFrequencyService
                .findPaymentFrequencyById(contractSignupRequest.getPaymentId());
        GatherFrequencyDto gatherFrequencyDto = gatherFrequencyService
                .findGatherFrequencyById(contractSignupRequest.getGatherId());
        WasteTypeDto wasteTypeDto = wasteTypeService
                .findWasteTypeById(contractSignupRequest.getWasteId());
        UnitDto unitDto = unitService
                .findUnitById(contractSignupRequest.getUnitId());

        ContractDto contractDto = new ContractDto()
                .setCustomerDto(customerDto)
                .setInitDate(contractSignupRequest.getInitDate())
                .setEndDate(contractSignupRequest.getEndDate())
                .setServiceOfferDto(serviceOfferDto)
                .setTotalCost(contractSignupRequest.getTotalCost())
                .setPaymentTypeDto(paymentTypeDto)
                .setPaymentFrequencyDto(paymentFrequencyDto)
                .setPayments(contractSignupRequest.getPayments())
                .setAmount(contractSignupRequest.getAmount())
                .setGatherFrequencyDto(gatherFrequencyDto)
                .setWasteTypeDto(wasteTypeDto)
                .setVolume(contractSignupRequest.getVolume())
                .setUnitDto(unitDto)
                .setDays(contractSignupRequest.getDays())
                .setStatus("Pendiente");

        return contractService.createContract(contractDto, customerId);
    }

}
