package com.example.enjoytrip.api.dao;
import com.example.enjoytrip.api.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogsRepository extends JpaRepository<Blogs, String> {
    Blogs findByBlogId(int blogId);
    List<Blogs> findByUserSeq(int userSeq);

}
