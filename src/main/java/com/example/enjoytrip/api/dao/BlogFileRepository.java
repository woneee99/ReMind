package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.entity.BlogFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogFileRepository extends JpaRepository<BlogFile, String> {
    List<BlogFile> findByBlogId(int blogId);
}
