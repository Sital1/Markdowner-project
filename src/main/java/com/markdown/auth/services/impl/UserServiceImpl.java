package com.markdown.auth.services.impl;

import com.google.common.base.Preconditions;
import com.markdown.auth.daos.RoleDAO;
import com.markdown.auth.daos.UserDAO;
import com.markdown.auth.dtos.UserInfoDTO;
import com.markdown.auth.dtos.UserLoginDTO;
import com.markdown.auth.models.MarkDownRoleModel;
import com.markdown.auth.models.MarkDownUserModel;
import com.markdown.auth.services.TokenService;
import com.markdown.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String UNKNOWN_USERNAME_OR_BAD_PASSWORD = "unknown username or bad password";

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserDAO userDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    TokenService tokenService;

    @Override
    public void createUser(UserInfoDTO userInfoDTO) {

        // transform userInfoDTO to markdownUserModel

        checkNotNull(userInfoDTO.getPassword());

        MarkDownUserModel markDownUserModel = modelMapper.map(userInfoDTO, MarkDownUserModel.class);

        // need to hash the password before we save it
        markDownUserModel.setPassword(bCryptPasswordEncoder.encode(userInfoDTO.getPassword()));


        //assign default role when the user is created
        markDownUserModel.setRoles(
                roleDAO.findAll().stream()
                        .map(MarkDownRoleModel::getRole)
                        .filter(role -> role.contains("USER"))
                        .collect(Collectors.toList())
        );


        //generate a new token foe the user
        tokenService.generateToken(markDownUserModel);

        // save markDownUserModel(Because there are other fields we need to instantiate)

        userDAO.save(markDownUserModel);

        // set the pw do empty
        userInfoDTO.setPassword("");

        //update to userInfoDTO when the markdown model has been saved
        modelMapper.map(markDownUserModel, userInfoDTO);

    }

    @Override
    public UserInfoDTO getUserInfo(String userId) {

        Optional<MarkDownUserModel> optionalMarkDownUserModel = userDAO.findById(userId);
        if (optionalMarkDownUserModel.isPresent()) {
            return modelMapper.map(optionalMarkDownUserModel.get(), UserInfoDTO.class);
        }

        return null;
    }

    @Override
    public UserInfoDTO loginUser(UserLoginDTO userLoginDTO) {

        Optional<MarkDownUserModel> optionalMarkDownUserModel = userDAO.findByUsername(userLoginDTO.getUsername());

        if (optionalMarkDownUserModel.isPresent()) {
            MarkDownUserModel markDownUserModel = optionalMarkDownUserModel.get();

            // check if password match
            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), markDownUserModel.getPassword())) {
                return modelMapper.map(markDownUserModel, UserInfoDTO.class);
            } else {
                throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);
            }

        } else {
            throw new BadCredentialsException(UNKNOWN_USERNAME_OR_BAD_PASSWORD);

        }

    }
}
