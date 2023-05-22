package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.entity.Blog;
import com.example.enjoytrip.api.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Blog>> blogList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                                               Pageable pageable) {
        Page<Blog> page = blogService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping
    public ResponseEntity<Integer> blogInsert(@RequestBody Blog blog) {
        Blog blogDto = blogService.blogInsert(blog);
        if(blogDto == null) return ResponseEntity.ok().body(0);
        else return ResponseEntity.ok().body(1);
    }
}
