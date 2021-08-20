package com.markdown.auth.services;

import io.jsonwebtoken.SigningKeyResolver;

import javax.crypto.SecretKey;

public interface AuthSigningKeyResolver extends SigningKeyResolver {

    SecretKey getSecretKey();
}
