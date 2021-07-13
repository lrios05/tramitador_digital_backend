package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.WasteTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class WasteTypeController {

    @Autowired
    private WasteTypeService wasteTypeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findwaste")
    public Response findWasteType(@PathVariable("id") Long wasteId){
        return Response.ok().setPayload(wasteTypeService.findWasteTypeById(wasteId));
    }

    @GetMapping("/listwaste")
    public Response listWasteTypes() {
        return Response.ok().setPayload(wasteTypeService.listAllWasteTypes());
    }
}
