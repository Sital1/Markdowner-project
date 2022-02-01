package com.markdown.auth.config.security;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {
    public AuthFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public AuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenUnstripped = request.getHeader(AUTHORIZATION);
        String token = StringUtils.removeStart(Optional.ofNullable(tokenUnstripped).orElse(""), "Bearer").trim();

        Authentication authentication;

        if (isEmpty(token)) {
            authentication = new UsernamePasswordAuthenticationToken("guest", "");
        } else {
            authentication = new UsernamePasswordAuthenticationToken("user", token);
        }

        return getAuthenticationManager().authenticate(authentication);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // if the authentication works we set the authentication on the context,  and we continue the chain of filter
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // failure then we send 403 forbidden and pass the reason for faliure
        response.setStatus(SC_FORBIDDEN);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", failed.getCause());
        jsonObject.put("errorMessage", failed.getMessage());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonObject);
        response.getWriter().flush();

    }
}
