package com.example.springsecurity.service.impl;

import com.example.springsecurity.Pojo.User;
import com.example.springsecurity.dao.UserMapper;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(String username,String password,String nickname) {
        User user=new User();
        Random random=new Random();
        String s="";
        for (int i=0;i<10;i++){
            s=s+random.nextInt(10);
        }
        s=s+"@qq.com";
        user.setEmail(s);
        user.setEnabled(true);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserface("123123123");
        user.setUsername(username);
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public User selectbyname(String username) {
        return userMapper.selectbyname(username);
    }
}
