package com.example.springsecurity.service;

import com.example.springsecurity.Pojo.Permission;
import com.example.springsecurity.Pojo.Role;
import com.example.springsecurity.Pojo.User;
import com.example.springsecurity.dao.PermissionMapper;
import com.example.springsecurity.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// 返回用户信息给 AuthenticationProvider 去验证
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过用户名找到用户的信息
        User user=userService.selectbyname(username);
        if(user!=null) {
            //通过用户的ID获得用户所拥有的权限
            List<Permission> permissions= permissionService.findByUserid(user.getId());
            List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            //把用户的权限加入用户的信息中
            user.setAuthorities(grantedAuthorities);
            //把用户信息返回给AuthenticationProvider
            return user;
        }
        return null;
    }
}
