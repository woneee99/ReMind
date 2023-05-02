package com.example.enjoytrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/trip")
    public String getTripArea() {
        return "trip-area.html";
    }
}
