package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, String> {
    List<HashTag> getHashTagByBlogId(int blogId);
}
