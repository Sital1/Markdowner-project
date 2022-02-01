package com.markdown.auth.services;

import com.markdown.auth.dtos.UserInfoDTO;
import com.markdown.auth.dtos.UserLoginDTO;

public interface UserService {

    // user creation
    void createUser(UserInfoDTO userInfoDTO);


    // fetch user
    UserInfoDTO getUserInfo(String userId);

    // login user
    UserInfoDTO loginUser(UserLoginDTO userLoginDTO);

}
