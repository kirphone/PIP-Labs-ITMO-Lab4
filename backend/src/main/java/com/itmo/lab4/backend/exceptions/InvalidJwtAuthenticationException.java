package com.itmo.lab4.backend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    public InvalidJwtAuthenticationException(){
        super("Expired or invalid JWT token");
    }

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
