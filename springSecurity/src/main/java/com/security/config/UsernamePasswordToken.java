package com.security.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 3935193827129146344L;
    private Object principal;

    private Object credentials;

    public UsernamePasswordToken(Object Username ,Object Password ) {
        super(null);
        this.principal= Username;
        this.credentials = Password;
    }


    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
