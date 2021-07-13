package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.serviceoffer.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "*")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping("/findtype/{id}")
    public Response findServiceType(@PathVariable("id") Long activityId){

        return Response.ok().setPayload(serviceTypeService.findServiceTypeById(activityId));
    }

    @GetMapping("/listtype")
    public Response findAllServiceTypes(){

        return Response.ok().setPayload(serviceTypeService.findAllServiceTypes());
    }
}
