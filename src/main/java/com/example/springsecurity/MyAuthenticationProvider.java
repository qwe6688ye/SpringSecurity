package com.example.springsecurity;

import com.example.springsecurity.Pojo.User;
import com.example.springsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;


//认证授权
// AuthenticationManager 将验证工作交给ProviderManager   而ProviderManager委托所配置的AuthenticationProvider列表 来验证
//验证用户通过后 授予用户权限  把用户信息保存在spring的全局缓存SecurityContextHolder  好让后面访问资源时 能够调用
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password = (String)authentication.getCredentials();// 这个是表单中输入的密码；
        // 这里构建来判断用户是否存在和密码是否正确
        User userInfo = (User) userDetailService.loadUserByUsername(userName); // 这里调用我们的自己写的获取用户的方法；
        if (userInfo == null) {
            throw new BadCredentialsException("用户名不存在");
        }
        if (!passwordEncoder.matches(password,userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities(); //getAuthorities()是获取权限信息的方法
        // 构建返回的用户登录成功的token
            return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
