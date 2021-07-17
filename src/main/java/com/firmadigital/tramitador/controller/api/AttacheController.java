package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.AttacheRequest;
import com.firmadigital.tramitador.dto.mapper.DocumentMapper;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
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
import java.util.ArrayList;
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

    @GetMapping("/list")
    public Response listAttaches(){
        return Response.ok().setPayload(attacheService.findAllAttaches());
    }

    @PostMapping("/create/{id}")
    public Response createAttache (@PathVariable("id") Long contractId,
                                 @Valid @RequestBody AttacheRequest attacheRequest) {

        return Response.ok().setPayload(registerAttache(contractId, attacheRequest));
    }

    private AttacheDto registerAttache(Long contractId, AttacheRequest attacheRequest) {

        Long userId = attacheRequest.getUserId();
        ContractDto contractDto = contractService.findContractById(contractId);
        UserDto userDto = userService.findById(userId);

        AttacheDto attacheDto = new AttacheDto()
                .setContractDto(contractDto)
                .setUserDto(userDto)
                .setOriginUrl(attacheRequest.getOriginUrl())
                .setDocumentDtoList(attacheRequest.getDocuments()
                        .stream().map(document -> {
                            return DocumentMapper.toDocumentDto(document);
                        }).collect(Collectors.toList())
                );

        return attacheService.createAttache(contractId, userId, attacheDto);
    }
}
