package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.NoteDetailRequest;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.tracking.DetailDto;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
import com.firmadigital.tramitador.service.tracking.DetailService;
import com.firmadigital.tramitador.service.tracking.InstructionService;
import com.firmadigital.tramitador.service.tracking.NoteService;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.YearMonth;
import java.util.Date;

@RestController
@RequestMapping("/api/followup")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private InstructionService instructionService;

    @GetMapping("/find/{id}")
    public Response findNote(@PathVariable("id") Long noteId){
        return Response.ok().setPayload(noteService.findById(noteId));
    }

    @GetMapping("/findbynote/{id}")
    public Response findByNoteId(@PathVariable("id") Long noteId){
        return Response.ok().setPayload(noteService.findByNoteId(noteId));
    }

    @GetMapping("/findcontractid/{id}")
    public Response findNoteContractId(@PathVariable("id") Long contractId){
        return Response.ok().setPayload(noteService.findByContractId(contractId));
    }

    @GetMapping("/findbycontract/{code}")
    public Response findNoteByContract(@PathVariable("code") String code){
        return Response.ok().setPayload(noteService.findByContractCode(code));
    }

    @GetMapping("/findbyparams")
    public Response findNotes(@RequestParam(required = false) String contractCode,
                                    @RequestParam(required = false) Long noteCode,
                                    @RequestParam(required = false) String status){

        if (contractCode != null && !contractCode.isEmpty()) {
            if (status != null && !status.isEmpty()) {
                return Response.ok().setPayload(noteService.findByContractAndStatus(contractCode, status));
            }
            return Response.ok().setPayload(noteService.findByContractCode(contractCode));
        } else if (noteCode > 0) {
            if (status != null && !status.isEmpty()) {
                return Response.ok().setPayload(noteService.findByNumberAndStatus(noteCode, status));
            }
            return Response.ok().setPayload(noteService.findNoteByNumber(noteCode));
        }
        return Response.ok().setPayload(noteService.findNoteByStatus(status));
    }

    @GetMapping("/findbynumber/{numcode}")
    public Response findNoteByNumber(@PathVariable("numcode") Long number){
        return Response.ok().setPayload(noteService.findNoteByNumber(number));
    }

    @GetMapping("/findbystatus/{status}")
    public Response findNoteByStatus(@PathVariable("status") String status){
        return Response.ok().setPayload(noteService.findNoteByStatus(status));
    }

    @GetMapping("/list")
    public Response findAllNotes(){
        return Response.ok().setPayload(noteService.findAll());
    }

    @PostMapping("/create/{id}")
    public Response createNote(@PathVariable("id") Long contractId,
                               @Valid @RequestBody NoteDetailRequest noteDetailRequest) {
        return Response.ok().setPayload(registerNote(contractId, noteDetailRequest));
    }

    private NoteDto registerNote(Long contractId, NoteDetailRequest noteRequest) {

        Long userId = noteRequest.getUserId();
        Long sequence = noteService.countAllNotes() + 1;
        int year = YearMonth.now().getYear();
        ContractDto contractDto = contractService.findContractById(contractId);

        NoteDto noteDto = new NoteDto()
                .setContractDto(contractDto)
                .setSequence(sequence)
                .setYear(year)
                .setSubject(noteRequest.getSubject())
                .setStatus("Pendiente")
                .setUserDto(getUserDto(userId));

        NoteDto newNoteDto = noteService.createNote(contractId, userId, noteDto);
        registerDetail(newNoteDto.getNoteId(), noteRequest);

        return newNoteDto;
    }

    private void registerDetail(Long noteId, NoteDetailRequest detailRequest) {

        Long fromUserId = detailRequest.getFromUserId();
        Long toUserId = detailRequest.getToUserId();
        NoteDto noteDto = noteService.findById(noteId);
        InstructionDto instructionDto = instructionService.findById(detailRequest.getInstructionId());

        DetailDto detailDto = new DetailDto()
                .setNoteDto(noteDto)
                .setComment(detailRequest.getComment())
                .setPriority(detailRequest.getPriority())
                .setDeadline(detailRequest.getDeadLine())
                .setFromUserDto(getUserDto(fromUserId))
                .setToUserDto(getUserDto(toUserId))
                .setSendDate(new Date())
                .setSendStatus("Concluido")
                .setReceivedStatus("Pendiente")
                .setInstructionDto(instructionDto);

        detailService.createDetail(noteId, fromUserId, toUserId, detailDto);
    }

    private UserDto getUserDto(Long id) {
        return userService.findById(id);
    }
}
