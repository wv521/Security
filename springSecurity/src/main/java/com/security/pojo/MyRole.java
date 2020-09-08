package com.security.pojo;

import org.springframework.security.core.GrantedAuthority;

public class MyRole  implements GrantedAuthority {


    private String role;


    @Override
    public String getAuthority() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
