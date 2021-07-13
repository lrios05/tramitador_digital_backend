package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.BusinessTypeRequest;
import com.firmadigital.tramitador.dto.model.customer.BusinessTypeDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.customer.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/business")
@CrossOrigin(origins = "*")
public class BusinessTypeController {

    @Autowired
    BusinessTypeService businessTypeService;

    @GetMapping("/businesstype/{id}")
    public Response findBusinessType(@PathVariable("id") Long typeId) {
        return Response.ok().setPayload(businessTypeService.findById(typeId));
    }

    @GetMapping("/listtype")
    public Response findAllBusinessTypes(){
        return Response.ok().setPayload(businessTypeService.listAllBusinessTypes());
    }

    @PostMapping("/signuptype")
    public Response signupBussinesTypes(@RequestBody @Valid BusinessTypeRequest businessTypeRequest) {

        return Response.ok().setPayload(registerBusinessType(businessTypeRequest));
    }

    private BusinessTypeDto registerBusinessType(BusinessTypeRequest businessTypeRequest) {

        BusinessTypeDto businessTypeDto = new BusinessTypeDto()
                .setTypeId(businessTypeRequest.getTypeId())
                .setBusinessType(businessTypeRequest.getBusinessType())
                .setDescription(businessTypeRequest.getDescription());

        return businessTypeService.createBusinessType(businessTypeDto);
    }
}
