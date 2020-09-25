package com.example.springsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class whoController {

    @RequestMapping("/whoim")
    public Object whoIm() {
//        String username="";
//        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        //principal是否是UserDetails 的一个实例       instanceof 用来测试一个对象是否是一个类的实例。
//        if (principal instanceof UserDetails) {
//             username = ((UserDetails)principal).getUsername();
//        } else {
//             username = principal.toString();
//        }
//        return username;
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
