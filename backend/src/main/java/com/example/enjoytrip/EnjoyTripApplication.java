package com.example.enjoytrip;

import com.example.enjoytrip.config.AppProperties;
import com.example.enjoytrip.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class EnjoyTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnjoyTripApplication.class, args);
    }

}
