package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.mapper.ActivityMapper;
import com.firmadigital.tramitador.dto.model.customer.ActivityDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.customer.Activity;
import com.firmadigital.tramitador.model.customer.ActivityGroup;
import com.firmadigital.tramitador.repository.customer.ActivityGroupRepository;
import com.firmadigital.tramitador.repository.customer.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.ACTIVITY_GROUP;
import static com.firmadigital.tramitador.exception.EntityType.BUSINESS_ACTIVITY;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityGroupRepository activityGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ActivityDto findById(Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);

        if (activity.isPresent()) {
            return ActivityMapper.toActivityDto(activity.get());
        }

        throw exception(BUSINESS_ACTIVITY, ENTITY_NOT_FOUND, activityId.toString());
    }

    @Override
    public ActivityDto createActivity(ActivityDto activityDto) {
        Optional<ActivityGroup> activityGroup = activityGroupRepository
                .findById(activityDto.getActivityGroupDto().getGroupId());

        if (activityGroup.isPresent()){
            Activity activity = new Activity()
                    .setActivity(activityDto.getActivity())
                    .setActivityGroup(activityGroup.get());

            return ActivityMapper.toActivityDto(activityRepository.save(activity));
        }

        throw exception(ACTIVITY_GROUP, ENTITY_NOT_FOUND, activityDto.getActivity());
    }

    @Override
    public ActivityDto updateActivity(ActivityDto activityDto) {
        return null;
    }

    @Override
    public List<ActivityDto> findAllActivities() {

        List<Activity> activityList = activityRepository.findAll();
        List<ActivityDto> activityDtoList = new ArrayList<>();

        activityList.forEach(activity -> {
            activityDtoList.add(ActivityMapper.toActivityDto(activity));
        });

        return activityDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
