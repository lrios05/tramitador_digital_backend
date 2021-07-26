package com.firmadigital.tramitador.service.signatures;

import com.firmadigital.tramitador.dto.mapper.ActivityMapper;
import com.firmadigital.tramitador.dto.mapper.ServiceTypeMapper;
import com.firmadigital.tramitador.dto.mapper.signatures.DigitalSignMapper;
import com.firmadigital.tramitador.dto.model.signatures.DigitalSignDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.customer.Activity;
import com.firmadigital.tramitador.model.signatures.DigitalSign;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.contract.ContractRepository;
import com.firmadigital.tramitador.repository.signatures.DigitalSignRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.*;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class DigitalSignServiceImpl implements DigitalSignService{
    @Autowired
    private DigitalSignRepository digitalSignRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DigitalSignDto findById(Long id) {
        Optional<DigitalSign> digitalSign = digitalSignRepository.findById(id);

        if (digitalSign.isPresent()) {
            return DigitalSignMapper.toDigitalSignDto(digitalSign.get());
        }

        throw exception(SIGNATURE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<DigitalSignDto> findByContractId(Long id) {

        return digitalSignRepository.findByContractId(id).stream().map(digitalSign -> {
            return DigitalSignMapper.toDigitalSignDto(digitalSign);
        }).collect(Collectors.toList());
    }

    @Override
    public DigitalSignDto createDigitalSign(DigitalSignDto digitalSignDto) {
        Long contractId = digitalSignDto.getContractDto().getContractId();
        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<User> user = userRepository.findById(digitalSignDto.getUserDto().getUserId());
            DigitalSign digitalSign = new DigitalSign()
                    .setContract(contract.get())
                    .setUser(user.get())
                    .setDocument(digitalSignDto.getDocument());

            return DigitalSignMapper.toDigitalSignDto(digitalSignRepository.save(digitalSign));
        }
        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contrato ID: " + contractId);
    }

    @Override
    public List<DigitalSignDto> findAll() {
        return digitalSignRepository.findAll().stream().map(digitalSign -> {
            return DigitalSignMapper.toDigitalSignDto(digitalSign);
        }).collect(Collectors.toList());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
