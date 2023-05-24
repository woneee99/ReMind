package com.example.enjoytrip.api.dao;
import com.example.enjoytrip.api.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, String> {
    Blog findByBlogId(int blogsId);
    List<Blog> findByUserSeq(int userSeq);
    Page<Blog> findAll(Pageable pageable);
}
