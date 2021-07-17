package com.firmadigital.tramitador.service.observation;

import com.firmadigital.tramitador.dto.model.observation.ObservationDto;
import com.firmadigital.tramitador.model.observation.Observation;

import java.util.List;

public interface ObservationService {

    ObservationDto findObservationById(Long id);
    ObservationDto findByContractCode(String code);
    List<ObservationDto> findAllByStatus(String status);
    ObservationDto findByContractCodeAndStatus(String code, String status);
    ObservationDto createObservation(Long contractId, Long userId, ObservationDto observationDto);
    ObservationDto updateObservation(Long contractId, ObservationDto observationDto);
    List<ObservationDto> findAllObservations();
}
