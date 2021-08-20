package com.markdown.auth.config;

import com.markdown.auth.dtos.UserInfoDTO;
import com.markdown.auth.models.MarkDownUserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        final TypeMap<MarkDownUserModel, UserInfoDTO> markDownUserModelUserInfoDTOTypeMap = modelMapper.typeMap(MarkDownUserModel.class, UserInfoDTO.class);

        markDownUserModelUserInfoDTOTypeMap.addMappings(mapping -> mapping.skip(UserInfoDTO::setPassword));

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
