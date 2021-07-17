package com.firmadigital.tramitador.service.upload;

import com.firmadigital.tramitador.dto.mapper.AttacheMapper;
import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.upload.Attache;
import com.firmadigital.tramitador.model.upload.Document;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.contract.ContractRepository;
import com.firmadigital.tramitador.repository.upload.AttacheRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.*;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class AttacheServiceImpl implements AttacheService{

    @Autowired
    private AttacheRepository attacheRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AttacheDto findByAttacheId(Long id) {
        Optional<Attache> attache = attacheRepository.findById(id);

        if (attache.isPresent()) {
            //return modelMapper.map(attache.get(), AttacheDto.class);
            return AttacheMapper.toAttacheDto(attache.get());
        }

        throw exception(ATTACHE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public AttacheDto createAttache(Long contractId, Long userId, AttacheDto attacheDto) {

        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<User> user = userRepository.findById(userId);

            Attache attache = new Attache()
                    .setContract(contract.get())
                    .setUser(user.get())
                    .setOriginUrl(attacheDto.getOriginUrl())
                    .setDocuments(attacheDto.getDocumentDtoList()
                            .stream().map(documentDto -> {
                                return new Document()
                                        .setDocType(documentDto.getDocType())
                                        .setDocName(documentDto.getDocName());
                            }).collect(Collectors.toList())
                    );

            return AttacheMapper.toAttacheDto(attacheRepository.save(attache));
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contract ID" + contractId);
    }

    @Override
    public AttacheDto updateAttache(AttacheDto attacheDto) {
        return null;
    }

    @Override
    public List<AttacheDto> findAllAttaches() {
        List<AttacheDto> attacheDtoList = new ArrayList<>();
        attacheRepository.findAll()
                .forEach(attache -> {
                    attacheDtoList.add(AttacheMapper.toAttacheDto(attache));
                });

        return attacheDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
