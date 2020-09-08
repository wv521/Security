package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {


    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("1231321");
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        username = username.trim();
        //构建security需要的UsernamePasswordToken
        UsernamePasswordToken authRequest = new UsernamePasswordToken(username, password);
        return this.getAuthenticationManager().authenticate(authRequest); //通过认证管理器来调用具体的自定义认证处理类 此处的为 MyAuthentiacationProvider
    }
}
