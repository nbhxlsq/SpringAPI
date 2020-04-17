package com.bluesky.myspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.bluesky.myspring","com.bluesky.myspring.service"})
public class MyspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringApplication.class, args);
    }

}
