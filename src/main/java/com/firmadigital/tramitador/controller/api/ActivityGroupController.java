package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.customer.ActivityGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business")
@CrossOrigin(origins = "*")
public class ActivityGroupController {

    @Autowired
    private ActivityGroupService activityGroupService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findgroup/{id}")
    public Response findActivityGroup(@PathVariable("id") Long groupId) {
        return Response.ok().setPayload(activityGroupService.findActivityGroupById(groupId));
    }

    @GetMapping("/listgroup")
    public Response findActivityGroup() {
        return Response.ok().setPayload(activityGroupService.findAllActivityGroups());
    }
}
