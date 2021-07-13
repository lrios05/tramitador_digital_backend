package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.PaymentFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class PaymentFrequencyController {

    @Autowired
    private PaymentFrequencyService paymentFrequencyService;

    @GetMapping("/findpaymentfrequency/{id}")
    public Response findPaymentFrequency(@PathVariable("id") Long paymentId){
        return Response.ok().setPayload(paymentFrequencyService.findPaymentFrequencyById(paymentId));
    }

    @GetMapping("/listpaymentfrequency")
    public Response listPaymentFrequencies() {
        return Response.ok().setPayload(paymentFrequencyService.findAllPaymentFrequencies());
    }

}
