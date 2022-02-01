package com.markdown.auth.services.impl;

import com.markdown.auth.services.AuthSigningKeyResolver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import static java.util.Objects.isNull;

@Component
public class AuthSigningKeyResolverImpl implements AuthSigningKeyResolver {

    @Value("${jwt.secret.key}")
    String secretKeyString;

    private SecretKey secretKey;

    @Override
    public SecretKey getSecretKey() {

        if(isNull(secretKey)){
            this.secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(this.secretKeyString.getBytes()));
        }
        return this.secretKey;
    }

    @Override
    public Key resolveSigningKey(JwsHeader header, Claims claims) {
        return getSecretKey();
    }

    @Override
    public Key resolveSigningKey(JwsHeader header, String plaintext) {
        return getSecretKey();
    }
}
