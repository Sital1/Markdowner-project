package com.markdown.auth.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDTO {

    private String username;
    private String displayName;
    private String password;
    private List<String> roles;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String id;
    private String jwtToken;


}
