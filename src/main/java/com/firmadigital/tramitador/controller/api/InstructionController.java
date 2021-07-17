package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.InstructionRequest;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.tracking.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "*")
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/find/{id}")
    public Response findInstruction(@PathVariable("id") Long instructionId) {
        return Response.ok().setPayload(instructionService.findById(instructionId));
    }

    @GetMapping("/list")
    public Response listAllInstructions() {
        return Response.ok().setPayload(instructionService.findAll());
    }

    @PostMapping("/create")
    public Response createInstruction(@Valid  @RequestBody InstructionRequest instructionRequest){

        InstructionDto instructionDto = new InstructionDto()
                .setInstruction(instructionRequest.getInstruction());
        return Response.ok().setPayload(instructionService.createInstruction(instructionDto));
    }

}
