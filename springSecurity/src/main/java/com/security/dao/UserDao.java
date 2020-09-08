package com.security.dao;

import com.security.pojo.MyRole;
import com.security.pojo.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    MyUserDetails getUserInfoByName(String username);

//    List<MyRole> getRoleList(Integer id);
}
