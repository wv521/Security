package com.security.service.impl;

import com.security.config.MyExeption;
import com.security.config.UsernamePasswordToken;
import com.security.pojo.MyRole;
import com.security.pojo.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthentiacationProvider  implements AuthenticationProvider {

    @Resource
    private UserDetailsService  userDetailsService;


    //处理认证
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //通过security中UserDetailsService接口中的loadUserByUsername(...)方法，获取用户信息。具体实现为自定义该接口的实现类 此处为：MyUserService
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
      if(userDetails==null)
          return null;
      if(!userDetails.getPassword().equals(authentication.getCredentials()))
          throw new MyExeption("登录密码错误");
        List<MyRole> myRoleList = new ArrayList<>();
        MyRole myRole = new MyRole();
        myRole.setRole("ROLE_ADMIN");
        myRoleList.add(myRole);
        userDetails.setAuthorities(myRoleList);
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(userDetails.getId(), userDetails.getPassword(), userDetails.getAuthorities());
        return usernamePassword;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordToken.class);
    }
}
