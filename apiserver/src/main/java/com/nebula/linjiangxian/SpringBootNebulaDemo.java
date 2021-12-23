package com.nebula.linjiangxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nebula.linjiangxian")
public class SpringBootNebulaDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootNebulaDemo.class, args);
    }
}
