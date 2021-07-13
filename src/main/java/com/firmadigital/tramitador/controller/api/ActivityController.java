package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.ActivityRequest;
import com.firmadigital.tramitador.dto.model.customer.ActivityDto;
import com.firmadigital.tramitador.dto.model.customer.ActivityGroupDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.customer.ActivityGroupService;
import com.firmadigital.tramitador.service.customer.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/business")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityGroupService activityGroupService;

    @GetMapping("/activity/{id}")
    public Response findActivity(@PathVariable("id") String activityId){

        return Response.ok().setPayload(activityService.findById(Long.getLong(activityId)));
    }

    @GetMapping("/listactivity/{id}")
    public Response listActivityByGroup(@PathVariable("id") Long groupId){

        return Response.ok().setPayload(activityService.findActivityByGroup(groupId));
    }

    @GetMapping("/listactivities")
    public Response listAllActivities(){

        return Response.ok().setPayload(activityService.findAllActivities());
    }

    @PostMapping("/signupactivity")
    public Response createActivity(@RequestBody @Valid ActivityRequest activityRequest) {
        return Response.ok().setPayload(registerActivity(activityRequest));
    }

    private ActivityDto registerActivity(ActivityRequest activityRequest) {

        ActivityGroupDto activityGroupDto = activityGroupService
                .findActivityGroupById(activityRequest.getGroupId());

        ActivityDto activityDto = new ActivityDto()
                .setActivity(activityRequest.getActivity())
                .setActivityGroupDto(activityGroupDto);

        return activityService.createActivity(activityDto);
    }
}
