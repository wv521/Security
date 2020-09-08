package com.security.service.impl;


import com.security.dao.UserDao;
import com.security.pojo.MyRole;
import com.security.pojo.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserService implements UserDetailsService {

    @Autowired(required=false)
    private UserDao userDao;


    //拼装用户权限信息
    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetails userInfoByName = userDao.getUserInfoByName(s);
        return userInfoByName;
    }
}
