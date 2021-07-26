package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.AttacheRequest;
import com.firmadigital.tramitador.controller.request.NoteDetailRequest;
import com.firmadigital.tramitador.dto.mapper.DocumentMapper;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.tracking.DetailDto;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.dto.model.upload.DocumentDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
import com.firmadigital.tramitador.service.tracking.DetailService;
import com.firmadigital.tramitador.service.tracking.InstructionService;
import com.firmadigital.tramitador.service.tracking.NoteService;
import com.firmadigital.tramitador.service.upload.AttacheService;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class AttacheController {

    //public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
    public static final String DIRECTORY = "G:/Downloads/uploads/";

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttacheService attacheService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private InstructionService instructionService;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            fileNames.add(fileName);
        }
        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String fileName) throws IOException {
        System.out.println("Llega el nombrecito: " + fileName);
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);

        if (!Files.exists((filePath))) {
            throw new FileNotFoundException(fileName + " no fue encontrado en el Servidor");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment:File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @GetMapping("/find/{id}")
    public Response findAttache(@PathVariable("id") Long attachId) {
        return Response.ok().setPayload(attacheService.findByAttacheId(attachId));
    }

    @GetMapping("/findbycontract/{code}")
    public Response findAttacheByContract(@PathVariable("code") String code) {
        return Response.ok().setPayload(attacheService.findByContractCode(code));
    }

    @GetMapping("/list")
    public Response listAttaches(){
        return Response.ok().setPayload(attacheService.findAllAttaches());
    }

    @PostMapping("/create")
    public Response createAttacheDocuments (@Valid @RequestBody AttacheRequest attacheRequest) {

        return Response.ok().setPayload(registerAttache(attacheRequest));
    }

    private AttacheDto registerAttache(AttacheRequest attacheRequest) {

        String contractCode = attacheRequest.getContractCode();
        Long userId = attacheRequest.getUserId();
        ContractDto contractDto = contractService.findByContractCode(contractCode);
        UserDto userDto = userService.findById(userId);

        AttacheDto attacheDto = new AttacheDto()
                .setContractDto(contractDto)
                .setUserDto(userDto)
                .setOriginUrl(DIRECTORY)
                .setDocumentDtoList(attacheRequest.getDocuments()
                        .stream().map(doc -> {
                            DocumentDto documentDto = new DocumentDto()
                                .setDocName(doc)
                                .setDocType("");
                            return documentDto;
                            //return DocumentMapper.toDocumentDto(document);
                        }).collect(Collectors.toList())
                );

        attacheDto = attacheService.createAttache(contractDto.getContractId(), userId, attacheDto);

        registerNote(contractDto, userDto, attacheRequest);

        return attacheDto;
    }

    private void registerNote(ContractDto contractDto, UserDto userDto, AttacheRequest attacheRequest) {
        Long sequence = noteService.countAllNotes();
        int year = YearMonth.now().getYear();

        NoteDto noteDto = new NoteDto()
                .setContractDto(contractDto)
                .setSequence(sequence)
                .setYear(year)
                .setSubject(attacheRequest.getSubject())
                .setStatus("Pendiente")
                .setUserDto(userDto);

        NoteDto newNoteDto = noteService.createNote(contractDto.getContractId(), userDto.getUserId(), noteDto);

        registerDetail(newNoteDto, attacheRequest);
    }

     private void registerDetail(NoteDto noteDto, AttacheRequest attacheRequest) {

        Long fromUserId = attacheRequest.getFromUserId();
        Long toUserId = attacheRequest.getToUserId();
        InstructionDto instructionDto = instructionService.findById(attacheRequest.getInstructionId());

        DetailDto detailDto = new DetailDto()
                .setNoteDto(noteDto)
                .setComment("Adjunto documentos")
                .setPriority(attacheRequest.getPriority())
                .setDeadline(attacheRequest.getDeadLine())
                .setFromUserDto(getUserDto(fromUserId))
                .setToUserDto(getUserDto(toUserId))
                .setSendDate(new Date())
                .setSendStatus("Concluido")
                .setReceivedStatus("Pendiente")
                .setInstructionDto(instructionDto);

        detailService.createDetail(noteDto.getNoteId(), fromUserId, toUserId, detailDto);
    }

    private UserDto getUserDto(Long id) {
        return userService.findById(id);
    }
}
