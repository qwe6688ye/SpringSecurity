package com.example.springsecurity.controller;

import com.example.springsecurity.Pojo.User;
import com.example.springsecurity.dao.UserMapper;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String getUser(){

        return "Hello Secuity";
    }
    @RequestMapping("/insert")
    @ResponseBody
    public void insert(String username,String password,String nickname,HttpServletResponse response) throws IOException {
       int i= userService.insertSelective(username,password,nickname);
       if(i>0){
           response.setContentType("application/json;charset=utf-8");
           String s="{\"code\":\"200\",\"data\":\"插入成功!\"}";
           response.getWriter().write(s);
       }else {
           response.setContentType("application/json;charset=utf-8");
           String s="{\"code\":\"201\",\"data\":\"插入失败!\"}";
           response.getWriter().write(s);
       }
    }
    @RequestMapping("/select")
    @ResponseBody
    public User select(String username){
        return userService.selectbyname(username);
    }

}
