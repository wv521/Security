package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class MyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> param  = new HashMap<>();
        param.put("code",200);
        param.put("message","登录成功");
        param.put("data",authentication);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(objectMapper.writeValueAsString(param));
        out.flush();
        out.close();
//        String token = Jwts.builder()
//                .setSubject( authentication.getPrincipal().toString())
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
//                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
//                .compact();
//        httpServletResponse.addHeader("Authorization", "Bearer " + token);
//        httpServletResponse.sendRedirect("/index");
    // 会帮我们跳转到上一次请求的页面上
//            super.onAuthenticationSuccess(request, response, authentication);

    }
}
