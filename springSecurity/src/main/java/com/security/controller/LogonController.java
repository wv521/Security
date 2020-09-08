package com.security.controller;


import com.security.config.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogonController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public String logon(){
        return "login.html";
    }

    @RequestMapping("/errorPage")
//    @ResponseBody
    public void  logonError(HttpServletRequest req,HttpServletResponse  rep){
        rep.setContentType("text/html;charset=utf-8");
        AuthenticationException exception = (AuthenticationException) req.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//        AuthenticationException exception = (AuthenticationException) req.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            rep.getWriter().write(String.valueOf(exception));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "登录错误";
    }

    @RequestMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username,String password){

        AbstractAuthenticationToken token = null;
        token=   new UsernamePasswordToken(username,password);

        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return "asdfds";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public  String hello(){
        return "hello";
    }

    @RequestMapping("/index")
    public String tologon(HttpServletRequest req, HttpServletResponse rep ){
        return "index.html";
    }
@RequestMapping("/logoutLogon")
    public String logout(){
        return "login.html";
}

}
