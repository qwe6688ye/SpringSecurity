package com.example.springsecurity;

import com.example.springsecurity.ResourceManager.MyFilterSecurityInterceptor;
import com.example.springsecurity.handler.MyAccessDeniedHandler;
import com.example.springsecurity.handler.MyAuthenticationEntryPoint;
import com.example.springsecurity.handler.MyAuthenticationFailHandler;
import com.example.springsecurity.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider provider;  //注入我们自己的AuthenticationProvider
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHander;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

        @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                    .antMatchers("/css/**","/index").permitAll()
//                    .antMatchers("/user/test").hasAnyRole("USER","ADMIN")
//                    .antMatchers("/loginerror").hasRole("ADMIN")
//                    .antMatchers("/user/insert").permitAll()
//                    .anyRequest().authenticated()
//                        .and()
//                .formLogin()
//                    .and()
//                    .csrf().disable()
//                    .formLogin().loginPage("/login").permitAll()
//                    .loginProcessingUrl("/form")
//                    .successHandler(myAuthenticationSuccessHandler)
//                    .failureHandler(myAuthenticationFailHander)
//                .and()
//                    .logout()
//                    .logoutSuccessUrl("/whoim").permitAll();
            http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

//            http.exceptionHandling()
//                    .authenticationEntryPoint(myAuthenticationEntryPoint)
//                    .accessDeniedHandler(myAccessDeniedHandler);
            http.
                    authorizeRequests()
                    .antMatchers("/home/insert")
                    .permitAll()
                    .anyRequest().authenticated()//任何资源访问都需要身份认证
                    .and()
                    .csrf().disable()

                    .formLogin().loginPage("/login").permitAll()
                    .loginProcessingUrl("/form")
                    .successHandler(myAuthenticationSuccessHandler)
;        }

    //配置我们自定义的验证授权  让 spring security使用我们自定义的验证器 而不是默认的验证器  也就是MyAuthenticationProvider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        //注册一个自定义的 AuthenticationProvider
        auth.authenticationProvider(provider);
        //再注册一个基于内存的 AuthenticationProvider
//        auth
//        .inMemoryAuthentication()
//            .withUser("admin").password("123456").roles("USER")
//            .and()
//            .withUser("test").password("test123").roles("ADMIN");
    }

    @Bean
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
