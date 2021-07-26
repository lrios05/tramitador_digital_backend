package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.RoleRequest;
import com.firmadigital.tramitador.dto.mapper.RoleMapper;
import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/role")
@CrossOrigin(origins = "*")
public class RolleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/find/{id}")
    public Response findRole(@PathVariable("id") Long roleId){
        return Response.ok().setPayload(roleService.findRoleById(roleId));
    }

    @GetMapping("/list")
    public Response findAllRoles(){
        return Response.ok().setPayload(roleService.findAllRoles());
    }
    @PostMapping("/create")
    public Response createRole(@Valid @RequestBody RoleRequest roleRequest){
        return Response.ok().setPayload(registerRole(roleRequest));
    }

    private RoleDto registerRole(RoleRequest roleRequest) {
        RoleDto roleDto = new RoleDto()
                .setRole(roleRequest.getRole())
                .setDescription(roleRequest.getDescription());

        return roleService.createRole(roleDto);
    }
}
