package com.yc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringbootJpaApplication {


    public static void main(String[] args) {

        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

}
