package com.markdown.auth.config;

import com.markdown.auth.config.security.MarkdownAuthProvider;
import com.markdown.auth.dtos.UserInfoDTO;
import com.markdown.auth.models.MarkDownUserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


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


    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        //GET,POST,HEAD
        config.applyPermitDefaultValues();

        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);

        configSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(configSource);
    }

    @Bean
    MarkdownAuthProvider markdownAuthProvider(){

        return new MarkdownAuthProvider();
    }

}
