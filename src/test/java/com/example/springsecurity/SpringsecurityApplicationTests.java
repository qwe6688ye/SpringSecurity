package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecurityApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder1=new BCryptPasswordEncoder();

    }

}
