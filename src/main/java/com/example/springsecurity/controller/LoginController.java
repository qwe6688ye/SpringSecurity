package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class    LoginController {
    @RequestMapping("/login")
    public String userlogin(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/loginerror")
    public String userloginerror(){
        return "loginerror";
    }
}
