package com.markdown.auth.services.impl;

import com.markdown.auth.exceptions.InvalidTokenException;
import com.markdown.auth.models.MarkDownUserModel;
import com.markdown.auth.services.AuthSigningKeyResolver;
import com.markdown.auth.services.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    AuthSigningKeyResolver authSigningKeyResolver;

    @Override
    public void validateToken(String jwtToken) throws InvalidTokenException {

        try
        {
            Jwts.parserBuilder()
                    .setSigningKeyResolver(authSigningKeyResolver)
                    .build()
                    .parse(jwtToken);
        }catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e)
        {
            throw new InvalidTokenException("Invalid Token", e);
        }

    }

    @Override
    public void generateToken(MarkDownUserModel markDownUserModel) {

        // generate token using JWTS builder

        String jwtToken;

        jwtToken = Jwts.builder()
                .setSubject(markDownUserModel.getUsername())
                .setAudience(markDownUserModel.getRoles().toString())
                .signWith(authSigningKeyResolver.getSecretKey(), SignatureAlgorithm.HS512)
                .compact();


        markDownUserModel.setJwtToken(jwtToken);


    }
}
