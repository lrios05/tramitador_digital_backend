package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.model.customer.ActivityDto;

import java.util.List;

public interface ActivityService {

    ActivityDto findById(Long activityId);

    ActivityDto createActivity(ActivityDto activityDto);

    ActivityDto updateActivity(ActivityDto activityDto);

    List<ActivityDto> findActivityByGroup(Long id);

    List<ActivityDto> findAllActivities();
}
