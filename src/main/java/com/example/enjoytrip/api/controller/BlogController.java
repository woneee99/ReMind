package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dao.BlogFileRepository;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogListDto;
import com.example.enjoytrip.api.dto.BlogPostDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.entity.Blog;
import com.example.enjoytrip.api.entity.BlogFile;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogFileRepository fileRepository;

    @GetMapping("/list")
    public ResponseEntity<List<BlogListDto>> blogList(@PageableDefault(size = 9, sort = "blogId", direction = Sort.Direction.DESC)
                                               Pageable pageable) {
        System.out.println("pageable = " + pageable);
        Page<Blog> page = blogService.findAll(pageable);
        Page<BlogListDto> blogListDto = BlogListDto.toDtoList(page);
        System.out.println("blogListDto.getContent() = " + blogListDto.getContent());
        List<BlogListDto> content = blogListDto.getContent();

        return ResponseEntity.ok().body(content);
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
    public ResponseEntity<Integer> blogInsert(@RequestBody BlogPostDto blogPostDto) {

        System.out.println("blog = " + blogPostDto.getContent());
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        UserDto user = userService.getUser(username);

        Blog blog = new Blog(blogPostDto);
        blog.setThumbNail(blogPostDto.getFileList().get(0));
        blog.setUserSeq(user.getUserSeq());
        blog.setCreatedAt(LocalDateTime.now());

        Blog blogDto = blogService.blogInsert(blog);

        for( String fileUrl : blogPostDto.getFileList()) {
            BlogFile blogFile1 = new BlogFile();
            blogFile1.setBlogUrl(fileUrl);
            blogFile1.setBlogId(blogDto);
            fileRepository.save(blogFile1);
        }

        if(blogDto == null) return ResponseEntity.ok().body(0);
        else return ResponseEntity.ok().body(1);
    }
}
