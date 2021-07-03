package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.mapper.ActivityGroupMapper;
import com.firmadigital.tramitador.dto.model.customer.ActivityGroupDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.customer.ActivityGroup;
import com.firmadigital.tramitador.repository.customer.ActivityGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.ACTIVITY_GROUP;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ActivityGroupServiceImpl implements ActivityGroupService{

    @Autowired
    ActivityGroupRepository activityGroupRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ActivityGroupDto findActivityGroupById(Long groupId) {

        Optional<ActivityGroup> activityGroup = activityGroupRepository.findById(groupId);

        if (activityGroup.isPresent()) {
            return modelMapper.map(activityGroup.get(), ActivityGroupDto.class);
        }

        throw exception(ACTIVITY_GROUP, ENTITY_NOT_FOUND, groupId.toString());
    }

    @Override
    public ActivityGroupDto createActivityGroup(ActivityGroupDto activityGroupDto) {

        ActivityGroup activityGroup = new ActivityGroup()
                .setActivityGroup(activityGroupDto.getActivityGroup());

        return ActivityGroupMapper.toActivityGroupDto(activityGroupRepository.save(activityGroup));
    }

    @Override
    public ActivityGroupDto updateActivityGroup(ActivityGroupDto activityGroupDto) {
        return null;
    }

    @Override
    public List<ActivityGroupDto> findAllActivityGroups() {
        List<ActivityGroupDto> activityGroupDtoList = new ArrayList<>();
        activityGroupRepository.findAll()
                .forEach(activityGroup -> {
                    activityGroupDtoList.add(ActivityGroupMapper.toActivityGroupDto(activityGroup));
                });

        return activityGroupDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
