package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Blog blogInsert(Blog blog);
    Page<Blog> findAll(Pageable pageable);
    Optional<Blog> boardDetail(int boardId);
}
