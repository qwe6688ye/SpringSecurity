package com.example.springsecurity.service;

import com.example.springsecurity.Pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    int deleteByPrimaryKey(Integer id);


    int insert(User record);

    int insertSelective(String username,String password,String nickname);

    User selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectbyname(String username);
}
