package com.firmadigital.tramitador.service.observation;

import com.firmadigital.tramitador.dto.mapper.ObservationMapper;
import com.firmadigital.tramitador.dto.model.observation.ObservationDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.observation.Comment;
import com.firmadigital.tramitador.model.observation.Observation;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.contract.ContractRepository;
import com.firmadigital.tramitador.repository.observation.ObservationRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.*;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ObservationServiceImpl implements ObservationService{

    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ObservationDto findObservationById(Long id) {
        Optional<Observation> observation = observationRepository.findById(id);

        if (observation.isPresent()) {
            //return modelMapper.map(attache.get(), AttacheDto.class);
            return ObservationMapper.toObservationDto(observation.get());
        }

        throw exception(OBSERVATION, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public ObservationDto findByContractCode(String code) {
        Optional<Observation> observation = observationRepository.findByContractCode(code);

        if (observation.isPresent()) {
            //return modelMapper.map(attache.get(), AttacheDto.class);
            return ObservationMapper.toObservationDto(observation.get());
        }

        throw exception(OBSERVATION, ENTITY_NOT_FOUND, code);
    }

    @Override
    public List<ObservationDto> findAllByStatus(String status) {
        List<ObservationDto> observationDtoList = new ArrayList<>();
        observationRepository.findAllByStatus(status)
                .forEach(observation -> {
                    observationDtoList.add(ObservationMapper.toObservationDto(observation));
                });

        return observationDtoList;
    }

    @Override
    public ObservationDto findByContractCodeAndStatus(String code, String status) {
        Optional<Observation> observation = observationRepository.findByContractCodeAndStatus(code, status);

        if (observation.isPresent()) {
            //return modelMapper.map(attache.get(), AttacheDto.class);
            return ObservationMapper.toObservationDto(observation.get());
        }

        throw exception(OBSERVATION, ENTITY_NOT_FOUND, code);
    }

    @Override
    public ObservationDto createObservation(Long contractId, Long userId, ObservationDto observationDto) {
        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<User> user = userRepository.findById(userId);

            Observation observation = new Observation()
                    .setContract(contract.get())
                    .setUser(user.get())
                    .setStatus(observationDto.getStatus())
                    .setComments(observationDto.getCommentDtoList()
                            .stream().map(commentDto -> {
                                return new Comment()
                                        .setComment(commentDto.getComment())
                                        .setStatus(commentDto.getStatus());
                            }).collect(Collectors.toList())
                    );

            return ObservationMapper.toObservationDto(observationRepository.save(observation));
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contract ID" + contractId);
    }

    @Override
    public ObservationDto updateObservation(Long contractId, ObservationDto observationDto) {
        return null;
    }


    @Override
    public List<ObservationDto> findAllObservations() {
        List<ObservationDto> observationDtoList = new ArrayList<>();
        observationRepository.findAll()
                .forEach(observation -> {
                    observationDtoList.add(ObservationMapper.toObservationDto(observation));
                });

        return observationDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
