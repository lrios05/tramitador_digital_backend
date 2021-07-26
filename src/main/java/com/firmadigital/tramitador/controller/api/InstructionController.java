package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.InstructionRequest;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.tracking.InstructionService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/followup")
@CrossOrigin(origins = "*")
public class InstructionController {

    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @GetMapping("/findinstruction/{id}")
    public Response findInstruction(@PathVariable("id") Long instructionId) {
        return Response.ok().setPayload(instructionService.findById(instructionId));
    }

    @GetMapping("/listinstructions")
    public Response listAllInstructions() {
        return Response.ok().setPayload(instructionService.findAll());
    }

    @PostMapping("/createinstruction")
    public Response createInstruction(@Valid  @RequestBody InstructionRequest instructionRequest){

        InstructionDto instructionDto = new InstructionDto()
                .setInstruction(instructionRequest.getInstruction());
        return Response.ok().setPayload(instructionService.createInstruction(instructionDto));
    }

}
