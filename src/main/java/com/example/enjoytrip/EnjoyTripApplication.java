package com.example.enjoytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@SpringBootApplication
public class EnjoyTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnjoyTripApplication.class, args);
    }

}
