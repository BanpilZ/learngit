package com.banpil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.banpil")
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}
