package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.BusinessSignupRequest;
import com.firmadigital.tramitador.dto.model.customer.ActivityDto;
import com.firmadigital.tramitador.dto.model.customer.BusinessDto;
import com.firmadigital.tramitador.dto.model.customer.BusinessTypeDto;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.customer.ActivityService;
import com.firmadigital.tramitador.service.customer.BusinessService;
import com.firmadigital.tramitador.service.customer.BusinessTypeService;
import com.firmadigital.tramitador.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/business")
@CrossOrigin(origins = "*")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BusinessTypeService businessTypeService;

    @Autowired
    ActivityService activityService;

    @GetMapping("/find/{id}")
    public Response findBusiness(@PathVariable("id") Long businessId) {
        return Response.ok().setPayload(businessService.findBusinessById(businessId));
    }

    @GetMapping("/findcustomer/{id}")
    public Response findByCustomerId(@PathVariable("id") Long customerId) {
        return Response.ok().setPayload(businessService.findByCustomerId(customerId));
    }

    @GetMapping("/list")
    public Response listBusiness(Pageable pageable) {
        return Response.ok().setPayload(businessService.findAllBusiness(pageable));
    }

    @PostMapping("/signup/{customerId}")
    public Response createBusiness(@PathVariable ("customerId") Long customerId,
                                   @Valid @RequestBody BusinessSignupRequest businessSignupRequest) {
        return Response.ok().setPayload(registerBusiness(customerId, businessSignupRequest));
    }

    private BusinessDto registerBusiness(Long customerId, BusinessSignupRequest businessSignupRequest){

        BusinessTypeDto businessTypeDto = businessTypeService.findById(businessSignupRequest.getTypeId());
        ActivityDto activityDto = activityService.findById(businessSignupRequest.getActivityId());

        CustomerDto customerDto = customerService.findById(customerId);

        BusinessDto businessDto = new BusinessDto()
                .setCustomerDto(customerDto)
                .setNit(businessSignupRequest.getNit())
                .setName(businessSignupRequest.getName())
                .setMobile(businessSignupRequest.getMobile())
                .setPhone(businessSignupRequest.getPhone())
                .setEmail(businessSignupRequest.getEmail())
                .setWebsite(businessSignupRequest.getWebsite())
                .setAddress(businessSignupRequest.getAddress())
                .setBusinessTypeDto(businessTypeDto)
                .setActivityDto(activityDto);

        return businessService.createBusiness(businessDto);
    }

    @PutMapping("/update")
    public BusinessDto updateBusiness(@RequestBody @Valid BusinessSignupRequest businessSignupRequest) {
        return null;
    }
}
