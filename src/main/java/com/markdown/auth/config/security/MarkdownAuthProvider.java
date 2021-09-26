package com.markdown.auth.config.security;

/*
    Provides with user based on some info. User related to current spring config.
    User is not the user info but user detail thai is part of the Spring EcoSystem.
*/

import com.markdown.auth.daos.UserDAO;
import com.markdown.auth.exceptions.InvalidTokenException;
import com.markdown.auth.exceptions.MarkDownTokenAuthException;
import com.markdown.auth.models.MarkDownUserModel;
import com.markdown.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
public class MarkdownAuthProvider  extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenService tokenService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        final String token = (String) authentication.getCredentials();// this will be the jwt token

        if (isEmpty(token))
        {
            return new User(username,"", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }

        // find user based on the token
        Optional<MarkDownUserModel> markDownUserModelOptional = userDAO.findByJwtToken(token);

        if (markDownUserModelOptional.isPresent())
        {
            final MarkDownUserModel markDownUserModel = markDownUserModelOptional.get();

            try
            {
                tokenService.validateToken(token);

            } catch (InvalidTokenException e) {
                markDownUserModel.setJwtToken(null);
                userDAO.save(markDownUserModel);
                return null;
            }

            return new User(username,"",
             AuthorityUtils.createAuthorityList(
                   markDownUserModel.getRoles().stream()
                           .map(roleName -> "ROLE_"+roleName)
                           .toArray(String[]::new)
             )

            );
        }
        throw new MarkDownTokenAuthException("User not found for the token"+token);
    }
}
