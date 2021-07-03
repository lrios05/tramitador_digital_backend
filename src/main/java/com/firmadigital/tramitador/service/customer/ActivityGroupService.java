package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.model.customer.ActivityGroupDto;

import java.util.List;

public interface ActivityGroupService {

    ActivityGroupDto findActivityGroupById(Long groupId);

    ActivityGroupDto createActivityGroup(ActivityGroupDto activityGroupDto);

    ActivityGroupDto updateActivityGroup(ActivityGroupDto activityGroupDto);

    List<ActivityGroupDto> findAllActivityGroups();
}
