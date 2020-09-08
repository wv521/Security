package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class MyLoginFailureHandler  extends SimpleUrlAuthenticationFailureHandler {


    public MyLoginFailureHandler() {
        this.setDefaultFailureUrl("/errorPage");

    }



    //认证失败后如果想进入controller此方法不用实现
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        System.out.println("我是自定义登录失败的处理方法");
////        super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
//        HashMap<String, String> map = new HashMap<>(2);
//        map.put("uri", httpServletRequest.getRequestURI());
//        map.put("msg", "认证失败");
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        httpServletResponse.setCharacterEncoding("utf-8");
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String resBody = objectMapper.writeValueAsString(map);
//        PrintWriter printWriter = httpServletResponse.getWriter();
//        printWriter.print(resBody);
//        printWriter.flush();
//        printWriter.close();
//    }
}
