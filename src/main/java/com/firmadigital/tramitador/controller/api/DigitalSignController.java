package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.DigitalSignRequest;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.signatures.DigitalSignDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.ContractService;
import com.firmadigital.tramitador.service.signatures.DigitalSignService;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/signature")
@CrossOrigin(origins = "*")
public class DigitalSignController {

    @Autowired
    private DigitalSignService digitalSignService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;

    @GetMapping("/find/{id}")
    public Response findSignature(@PathVariable("id") Long id) {
        return Response.ok().setPayload(digitalSignService.findById(id));
    }

    @GetMapping("/findbycontract/{id}")
    public Response findSignatureByContract(@PathVariable("id") Long contractId) {
        return Response.ok().setPayload(digitalSignService.findById(contractId));
    }

    @PostMapping("/create")
    public Response createSignature(@Valid @RequestBody DigitalSignRequest digitalSignRequest) {
        return Response.ok().setPayload(registerDigitalSign(digitalSignRequest));
    }

    private DigitalSignDto registerDigitalSign(DigitalSignRequest digitalSignRequest) {
        ContractDto contractDto = contractService.findContractById(digitalSignRequest.getContractId());
        UserDto userDto = userService.findById(digitalSignRequest.getUserId());

        DigitalSignDto digitalSignDto = new DigitalSignDto()
                .setContractDto(contractDto)
                .setUserDto(userDto)
                .setDocument(digitalSignRequest.getDocument());
        return digitalSignService.createDigitalSign(digitalSignDto);
    }
}
