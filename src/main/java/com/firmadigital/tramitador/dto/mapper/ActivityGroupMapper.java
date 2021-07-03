package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.ActivityGroupDto;
import com.firmadigital.tramitador.model.customer.ActivityGroup;
import org.springframework.stereotype.Component;

@Component
public class ActivityGroupMapper {

    public static ActivityGroupDto toActivityGroupDto(ActivityGroup activityGroup){

        return new ActivityGroupDto()
                .setGroupId(activityGroup.getId())
                .setActivityGroup(activityGroup.getActivityGroup());
    }
}
