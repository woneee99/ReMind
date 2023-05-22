package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogRepository;
import com.example.enjoytrip.api.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    private BlogRepository blogRepository;

    @Override
    public Blog blogInsert(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> boardDetail(int boardId) {
        return Optional.empty();
    }
}
