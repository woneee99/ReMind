package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogDao;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogDao blogDao;

    @Override
    public int fileInsert(BlogFileDto fileDto) {
        return blogDao.fileInsert(fileDto);
    }

    @Override
    public int blogInsert(BlogDto blogDto) {
        return blogDao.blogInsert(blogDto);
    }

//
//    @Override
//    public Page<Blog> findAll(Pageable pageable) {
//        return blogRepository.findAll(pageable);
//    }
//
//    @Override
//    public Page<Blog> findAllByHashTag(Pageable pageable, String hashTag) {
//        return blogRepository.findAllByHashTag(pageable, hashTag);
//    }
//
//    @Override
//    public List<Blog> findByUserSeq(int userSeq) {
//        return blogRepository.findByUserSeq(userSeq);
//    }
//
//    @Override
//    public BlogDto blogDetail(int blogId) {
//        BlogDto blogDto = new BlogDto();
//        Blog blog = blogRepository.findByBlogId(blogId);
//        blogDto.setBlogId(blog.getBlogId());
//        blogDto.setContent(blog.getContent());
//        blogDto.setLikeCount(blog.getLikeCount());
//        blogDto.setCreatedAt(blog.getCreatedAt());
//        blogDto.setUserSeq(blog.getUserSeq());
//
//        UserDto user = userDao.findUserByUserSeq(blog.getUserSeq());
//        blogDto.setUserName(user.getUserName());
//        blogDto.setProfileImageUrl(user.getProfileImageUrl());
//
////        List<HashTag> hashTagList = hashTagRepository.getHashTagByBlogId(blogId);
////        blogDto.setHashTag(hashTagList);
////        System.out.println("hashTagList.size() = " + hashTagList.size());
//        List<BlogFile> fileList = blogFileRepository.findByBlogId(blog);
//        System.out.println("fileList.sz = " + fileList.size());
//
//        List<String> list = new ArrayList<>();
//        for(BlogFile bf : fileList) {
//            list.add(bf.getBlogUrl());
//        }
//        blogDto.setFileList(list);
//        return blogDto;
//    }

//    @Override
//    public Optional<Blogs> blogDetail(int blogId) {
//        return Optional.empty();
//    }
}
