package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.contract.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findunit")
    public Response findWasteType(@PathVariable("id") Long unitId){
        return Response.ok().setPayload(unitService.findUnitById(unitId));
    }

    @GetMapping("/listunits")
    public Response listWasteTypes() {
        return Response.ok().setPayload(unitService.findAllUnits());
    }

}
