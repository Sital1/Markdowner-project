package com.markdown.auth.controllers;

import com.markdown.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    // WOnt be called by frontend. CHeck if the request is legit or not.
    @GetMapping("/validate")
    @PreAuthorize("hasAnyRole('ANONYMOUS','USER','ADMIN')")
    public void validateToken(HttpServletRequest httpServletRequest) throws Exception
    {
       // throw new Exception("Some random exception");

        String header = httpServletRequest.getHeader(AUTHORIZATION);

        // Bearer schema

        String token = null;

        if (!isEmpty(header))
        {
           token = header.split("\\s")[1];
        }

        tokenService.validateToken(token);
    }

}
