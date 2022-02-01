package com.markdown.auth.exceptions;


import org.springframework.security.core.AuthenticationException;

public class MarkDownTokenAuthException extends AuthenticationException {
    public MarkDownTokenAuthException(String s) {
        super(s);
    }
}
