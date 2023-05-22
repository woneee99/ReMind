package com.example.enjoytrip.api.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.enjoytrip.api.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, String> {
    Page<Blog> findAll(Pageable pageable);
    Optional<Blog> findByBoardId(int boardId);
}
