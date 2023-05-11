package com.example.enjoytrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String getLogin() {
        return "sign-in.html";
    }

    @GetMapping("/trip")
    public String getTripArea() {
        return "trip-area.html";
    }
}
