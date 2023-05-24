package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.entity.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    Blogs blogsInsert(Blogs blogs);
    Page<Blogs> findAll(Pageable pageable);
    List<Blogs> findByUserSeq(int userSeq);
    BlogDto blogDetail(int blogId);
}
