package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.entity.Blogs;
import com.example.enjoytrip.api.service.BlogService;
import com.example.enjoytrip.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<Blogs>> blogList(@PageableDefault(sort = "blog_id", direction = Sort.Direction.DESC)
                                               Pageable pageable) {
        Page<Blogs> page = blogService.findAll(pageable);
        System.out.println("page = " + page);
        return ResponseEntity.ok().body(page);
    }

//    @GetMapping("/myList")
//    public ResponseEntity<List<Blogs>> myBlogList() {
//        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
//        String username = principal.getName();
//        UserDto user = userService.getUser(username);
//
//
//        return ResponseEntity.ok().body();
//    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable(name = "blogId") int blogId) {
        return ResponseEntity.ok().body(blogService.blogDetail(blogId));
    }

    @PostMapping
    public ResponseEntity<Integer> blogInsert(@RequestBody Blogs blogs) {
        Blogs blogsDto = blogService.blogsInsert(blogs);
        if(blogsDto == null) return ResponseEntity.ok().body(0);
        else return ResponseEntity.ok().body(1);
    }
}
