package com.markdown.auth.services.impl;

import com.markdown.auth.daos.RoleDAO;
import com.markdown.auth.dtos.RoleDTO;
import com.markdown.auth.models.MarkDownRoleModel;
import com.markdown.auth.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public void createRole(RoleDTO roleDTO) {

        // create a model from the DTO
        MarkDownRoleModel markDownRoleModel = modelMapper.map(roleDTO, MarkDownRoleModel.class);

        // save the model
        roleDAO.save(markDownRoleModel);

        // change the model back to the DTO
        modelMapper.map(markDownRoleModel,roleDTO);

    }

    @Override
    public RoleDTO getRoleInfo(String roleId) {

        Optional<MarkDownRoleModel> optionalMarkDownRoleModel = roleDAO.findById(roleId);

        if (optionalMarkDownRoleModel.isPresent())
        {
            MarkDownRoleModel markDownRoleModel = optionalMarkDownRoleModel.get();

            return modelMapper.map(markDownRoleModel,RoleDTO.class);
        }

            return null;
    }
}
