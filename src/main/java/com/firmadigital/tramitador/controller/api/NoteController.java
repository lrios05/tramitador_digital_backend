package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.NoteRequest;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
import com.firmadigital.tramitador.service.tracking.NoteService;
import com.firmadigital.tramitador.service.user.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;

    @GetMapping("/find/{id}")
    public Response findNote(@PathVariable("id") Long noteId){
        return Response.ok().setPayload(noteService.findById(noteId));
    }

    @GetMapping("/list")
    public Response findAllNotes(){
        return Response.ok().setPayload(noteService.findAll());
    }

    @PostMapping("/create/{id}")
    public Response createNote(@PathVariable("id") Long contractId,
                              @Valid @RequestBody NoteRequest noteRequest) {
        return Response.ok().setPayload(registerNote(contractId, noteRequest));
    }

    private NoteDto registerNote(Long contractId, NoteRequest request) {

        Long userId = request.getUserId();
        Long sequence = noteService.countAllNotes();
        int year = YearMonth.now().getYear();

        ContractDto contractDto = contractService.findContractById(contractId);
        UserDto userDto = userService.findById(userId);

        NoteDto noteDto = new NoteDto()
                .setContractDto(contractDto)
                .setNumber(sequence)
                .setYear(year)
                .setDeadline(request.getDeadLine())
                .setStatus("Pendiente")
                .setUserDto(userDto);

        return noteService.createNote(contractId, userId, noteDto);
    }
}
