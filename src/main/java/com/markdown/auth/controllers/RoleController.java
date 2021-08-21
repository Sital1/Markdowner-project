package com.markdown.auth.controllers;

import com.google.common.base.Preconditions;
import com.markdown.auth.dtos.RoleDTO;
import com.markdown.auth.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.*;

@RestControllerAdvice
@RequestMapping("/role")
@PreAuthorize("hasAnyRole('ADMIN')")
public class RoleController {

    // inject role service
    @Autowired
    RoleService roleService;


    // create a role
    @PostMapping("/create")
    public RoleDTO create(@RequestBody RoleDTO roleDTO) {
        checkNotNull(roleDTO);

        roleService.createRole(roleDTO);

        return roleDTO;
    }


    // get info about the role

    // create a role
    @GetMapping("info/{roleId}")
    public RoleDTO getRoleInfo(@PathVariable String roleId) {

        return roleService.getRoleInfo(roleId);

    }

    // delete a role

    // modify a role


}
