package com.security.config;

import org.springframework.security.core.AuthenticationException;

public class MyExeption extends AuthenticationException {
    public MyExeption(String msg) {
        super(msg);
    }
}
