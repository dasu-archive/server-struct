package com.dasu.gateway.common;


import com.dasu.config.jwt.JwtProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {

    @Autowired
    JwtProperties jwtProperties;
    @Test
    public void test() {

    }
}
