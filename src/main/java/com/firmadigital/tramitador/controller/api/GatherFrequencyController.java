package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.GatherFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class GatherFrequencyController {

    @Autowired
    private GatherFrequencyService gatherFrequencyService;

    @GetMapping("/findgatherfrequency/{id}")
    public Response findGaherFrequency(@PathVariable("id") Long gatherId){
        return Response.ok().setPayload(gatherFrequencyService.findGatherFrequencyById(gatherId));
    }

    @GetMapping("/listgatherfrequency")
    public Response listGatherFrequencies() {
        return Response.ok().setPayload(gatherFrequencyService.findAllGatherFrequencies());
    }
}
