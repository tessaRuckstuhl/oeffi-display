package com.example.display;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class DisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisplayApplication.class, args);
    }

}
