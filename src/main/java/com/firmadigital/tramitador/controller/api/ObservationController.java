package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.ObservationRequest;
import com.firmadigital.tramitador.dto.mapper.CommentMapper;
import com.firmadigital.tramitador.dto.mapper.ObservationMapper;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.observation.ObservationDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
import com.firmadigital.tramitador.service.observation.ObservationService;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluation")
@CrossOrigin(origins = "*")
public class ObservationController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObservationService observationService;

    @GetMapping("/find/{id}")
    public Response findObservation(@PathVariable("id") Long obsId){
        return Response.ok().setPayload(observationService.findObservationById(obsId));
    }

    @GetMapping("/findcodestatus")
    public Response findObservation(@RequestParam(required = false) String code,
                                    @RequestParam(required = false) String status){

        if (code == null) {
            return Response.ok().setPayload(observationService.findAllByStatus(status));
        } else if (status == null) {
            return Response.ok().setPayload(observationService.findByContractCode(code));
        }
        return Response.ok().setPayload(observationService.findByContractCodeAndStatus(code, status));
    }

    @GetMapping("/list")
    public Response findAllObservations(){
        return Response.ok().setPayload(observationService.findAllObservations());
    }

    @PostMapping("/create/{id}")
    public Response createObservation(@PathVariable("id") Long contractId,
                                      @Valid @RequestBody ObservationRequest observationRequest) {

        return Response.ok().setPayload(registerObservation(contractId, observationRequest));
    }

    private ObservationDto registerObservation(Long contractId, ObservationRequest request) {

        Long userId = request.getUserId();
        ContractDto contractDto = contractService.findContractById(contractId);
        UserDto userDto = userService.findById(userId);

        ObservationDto observationDto = new ObservationDto()
                .setContractDto(contractDto)
                .setUserDto(userDto)
                .setStatus(request.getStatus())
                .setCommentDtoList(request.getComments()
                        .stream().map(comment -> {
                            return CommentMapper.toCommentDto(comment);
                        }).collect(Collectors.toList())
                );

        return observationService.createObservation(contractId, userId, observationDto);
    }
}
