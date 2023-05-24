package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog blogInsert(Blog blog);
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findByUserSeq(int userSeq);
    BlogDto blogDetail(int blogsId);
}
