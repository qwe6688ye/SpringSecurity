package com.example.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController  {
    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin，拥有admin权限";
    }
        @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
