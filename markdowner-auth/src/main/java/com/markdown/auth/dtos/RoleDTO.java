package com.markdown.auth.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {

    private String role;
    private String id;
    private Date createdAt;
    private Date updatedAt;
}
