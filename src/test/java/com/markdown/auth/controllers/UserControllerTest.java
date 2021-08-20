package com.markdown.auth.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.markdown.auth.dtos.UserInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)

class UserControllerTest {

    private UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception
    {
        assertThat(userController).isNotNull();
    }

    @Test
    public void createShouldReturnTheUserInfoDTOSuppliedAsJson() throws Exception {

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername("Sital");
        userInfoDTO.setPassword("asda");
        userInfoDTO.setRoles(Arrays.asList("ADMIN","USER"));

        ObjectMapper objectMapper = new ObjectMapper();


        HttpEntity<UserInfoDTO> request = new HttpEntity<>(userInfoDTO);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/user/create")
                        .content(objectMapper.writeValueAsString(userInfoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(userInfoDTO)));

    }

}