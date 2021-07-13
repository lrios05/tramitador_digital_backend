package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.serviceoffer.ServiceOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "*")
public class ServiceOfferController {

    @Autowired
    private ServiceOfferService serviceOfferService;

    @GetMapping("/find/{id}")
    public Response findService(@PathVariable("id") Long serviceId){

        return Response.ok().setPayload(serviceOfferService.findById(serviceId));
    }

    @GetMapping("/list/{id}")
    public Response findServicesByGroup(@PathVariable("id") Long serviceTypeId){

        return Response.ok().setPayload(serviceOfferService.findServiceOfferByServiceTypeId(serviceTypeId));
    }

    @GetMapping("/listall")
    public Response findAllServices(){

        return Response.ok().setPayload(serviceOfferService.findAllServiceOffer());
    }
}
