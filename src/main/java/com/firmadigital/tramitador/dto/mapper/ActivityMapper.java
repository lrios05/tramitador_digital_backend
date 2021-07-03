package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.ActivityDto;
import com.firmadigital.tramitador.model.customer.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public static ActivityDto toActivityDto(Activity activity) {

        return new ActivityDto()
                .setActivityId(activity.getId())
                .setActivity(activity.getActivity())
                .setActivityGroupDto(ActivityGroupMapper.toActivityGroupDto(activity.getActivityGroup()));
    }
}
