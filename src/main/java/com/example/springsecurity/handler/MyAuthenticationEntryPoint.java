package com.example.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//用来解决匿名用户访问无权限资源时的异常
@Component
public class MyAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        map.put("code", "202");
        map.put("msg", "没有权限");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
