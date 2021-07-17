package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.DetailRequest;
import com.firmadigital.tramitador.controller.request.NoteRequest;
import com.firmadigital.tramitador.dto.model.tracking.DetailDto;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.service.tracking.DetailService;
import com.firmadigital.tramitador.service.tracking.InstructionService;
import com.firmadigital.tramitador.service.tracking.NoteService;
import com.firmadigital.tramitador.service.user.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/details")
@CrossOrigin(origins = "*")
public class DetailController {

    @Autowired
    private DetailService detailService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private InstructionService instructionService;

    @GetMapping("/find/{id}")
    public Response findDetail(@PathVariable("id") Long detailId){
        return Response.ok().setPayload(detailService.findById(detailId));
    }

    @GetMapping("/findnote/{id}")
    public Response findDetailsByNote(@PathVariable("id") Long noteId){
        return Response.ok().setPayload(detailService.findByNoteId(noteId));
    }

    @GetMapping("/list")
    public Response findAllDetails(){
        return Response.ok().setPayload(detailService.findAll());
    }

    @PostMapping("/create/{id}")
    public Response createDetail(@PathVariable("id") Long noteId,
                              @Valid @RequestBody DetailRequest detailRequest) {
        return Response.ok().setPayload(registerDetail(noteId, detailRequest));
    }

    private DetailDto registerDetail(Long noteId, DetailRequest detailRequest) {

        Long fromUserId = detailRequest.getFromUserId();
        Long toUserId = detailRequest.getToUserId();
        NoteDto noteDto = noteService.findById(noteId);
        InstructionDto instructionDto = instructionService.findById(detailRequest.getInstructionId());

        DetailDto detailDto = new DetailDto()
                .setNoteDto(noteDto)
                .setComment(detailRequest.getComment())
                .setPriority(detailRequest.getPriority())
                .setFromUserDto(getUserDto(fromUserId))
                .setToUserDto(getUserDto(toUserId))
                .setSendDate(detailRequest.getSendDate())
                .setSendStatus("Concluido")
                .setReceivedStatus("Pendiente")
                .setInstructionDto(instructionDto);

        return detailService.createDetail(noteId, fromUserId, toUserId, detailDto);
    }

    private UserDto getUserDto(Long id) {
        return userService.findById(id);
    }

}
