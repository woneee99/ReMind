package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogFileRepository;
import com.example.enjoytrip.api.dao.BlogsRepository;
import com.example.enjoytrip.api.dao.HashTagRepository;
import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.entity.BlogFile;
import com.example.enjoytrip.api.entity.Blogs;
import com.example.enjoytrip.api.entity.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogsRepository blogsRepository;
    private final BlogFileRepository blogFileRepository;
    private final HashTagRepository hashTagRepository;

    private final UserDao userDao;

    @Override
    public Blogs blogsInsert(Blogs blogs) {
        return blogsRepository.save(blogs);
    }

    @Override
    public Page<Blogs> findAll(Pageable pageable) {
        return blogsRepository.findAll(pageable);
    }

    @Override
    public List<Blogs> findByUserSeq(int userSeq) {
        return blogsRepository.findByUserSeq(userSeq);
    }

    @Override
    public BlogDto blogDetail(int blogId) {
        BlogDto blogDto = new BlogDto();
        Blogs blog = blogsRepository.findByBlogId(blogId);
        blogDto.setBlogId(blog.getBlogId());
        blogDto.setContent(blog.getContent());
        blogDto.setLikeCount(blog.getLikeCount());
        blogDto.setCreatedAt(blog.getCreatedAt());
        blogDto.setUserSeq(blog.getUserSeq());

        UserDto user = userDao.findUserByUserSeq(blog.getUserSeq());
        blogDto.setUserName(user.getUserName());
        blogDto.setProfileImageUrl(user.getProfileImageUrl());

        List<HashTag> hashTagList = hashTagRepository.getHashTagByBlogId(blogId);
        blogDto.setHashTag(hashTagList);
        List<BlogFile> fileList = blogFileRepository.findByBlogId(blogId);
        blogDto.setFileList(fileList);

        return blogDto;
    }

//    @Override
//    public Optional<Blogs> blogDetail(int blogId) {
//        return Optional.empty();
//    }
}
