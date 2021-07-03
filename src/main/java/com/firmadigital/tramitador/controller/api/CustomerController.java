package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.CustomerSignupRequest;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.customer.CustomerService;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/find/{id}")
    public Response getCustomer(@PathVariable("id") Long customerId){
        return Response.ok().setPayload(customerService.findById(customerId));
    }

    @GetMapping("/list")
    public Response getAllCustomers(Pageable pageable){
        return Response.ok().setPayload(customerService.findAllCustomers(pageable));
    }

    @PostMapping("/signup")
    public Response signupCustomer(@RequestBody @Valid CustomerSignupRequest customerSignupRequest){

        return Response.ok().setPayload(registerCustomer(customerSignupRequest));
    }

    private CustomerDto registerCustomer(CustomerSignupRequest customerSignupRequest) {

        UserDto userDto = userService.findById(customerSignupRequest.getUserId());

        CustomerDto customerDto = new CustomerDto()
                .setDni(customerSignupRequest.getDni())
                .setName(customerSignupRequest.getName())
                .setPaternal(customerSignupRequest.getPaternal())
                .setMaternal(customerSignupRequest.getMaternal())
                .setMobile(customerSignupRequest.getMobile())
                .setPhone(customerSignupRequest.getPhone())
                .setEmail(customerSignupRequest.getEmail())
                .setAddress(customerSignupRequest.getAddress())
                .setUserDto(userDto);

        return customerService.createCustomer(customerDto);
    }
}
