package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.BoardDto;
import com.example.enjoytrip.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    BoardService service;

    @GetMapping
    public ResponseEntity<List<BoardDto>> getBoardList() {
        return ResponseEntity.ok().body(service.getBoardList());
    }
}
