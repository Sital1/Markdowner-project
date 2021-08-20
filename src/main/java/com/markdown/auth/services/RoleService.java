package com.markdown.auth.services;

import com.markdown.auth.dtos.RoleDTO;

public interface RoleService {

    // role creation
    void createRole(RoleDTO roleDTO);

    // fetch role
    RoleDTO getRoleInfo(String roleId);


}
