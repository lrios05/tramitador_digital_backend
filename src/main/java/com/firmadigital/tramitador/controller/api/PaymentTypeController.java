package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping("/findtype/{id}")
    public Response findPaymentType(@PathVariable("id") Long typeId){
        return Response.ok().setPayload(paymentTypeService.findPaymentTypeById(typeId));
    }

    @GetMapping("/listtype")
    public Response listPaymentTypes() {
        return Response.ok().setPayload(paymentTypeService.findAllPaymentTypes());
    }

}
