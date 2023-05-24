package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogFileRepository;
import com.example.enjoytrip.api.dao.BlogRepository;
import com.example.enjoytrip.api.dao.HashTagRepository;
import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.entity.BlogFile;
import com.example.enjoytrip.api.entity.Blog;
import com.example.enjoytrip.api.entity.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;
    private final BlogFileRepository blogFileRepository;
    private final HashTagRepository hashTagRepository;

    private final UserDao userDao;

    @Override
    public Blog blogInsert(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> findByUserSeq(int userSeq) {
        return blogRepository.findByUserSeq(userSeq);
    }

    @Override
    public BlogDto blogDetail(int blogId) {
        BlogDto blogDto = new BlogDto();
        Blog blog = blogRepository.findByBlogId(blogId);
        blogDto.setBlogId(blog.getBlogId());
        blogDto.setContent(blog.getContent());
        blogDto.setLikeCount(blog.getLikeCount());
        blogDto.setCreatedAt(blog.getCreatedAt());
        blogDto.setUserSeq(blog.getUserSeq());

        UserDto user = userDao.findUserByUserSeq(blog.getUserSeq());
        blogDto.setUserName(user.getUserName());
        blogDto.setProfileImageUrl(user.getProfileImageUrl());

//        List<HashTag> hashTagList = hashTagRepository.getHashTagByBlogId(blogId);
//        blogDto.setHashTag(hashTagList);
//        System.out.println("hashTagList.size() = " + hashTagList.size());
        List<BlogFile> fileList = blogFileRepository.findByBlogId(blog);
        System.out.println("fileList.sz = " + fileList.size());

        List<String> list = new ArrayList<>();
        for(BlogFile bf : fileList) {
            list.add(bf.getBlogUrl());
        }
        blogDto.setFileList(list);
        return blogDto;
    }

//    @Override
//    public Optional<Blogs> blogDetail(int blogId) {
//        return Optional.empty();
//    }
}
