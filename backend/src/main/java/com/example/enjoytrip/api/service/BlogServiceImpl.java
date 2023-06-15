package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogDao;
import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogDao blogDao;
    private final UserDao userDao;
    private String uploadFolder = "C:\\upload";

    @Override
    public int fileInsert(BlogFileDto fileDto) {
        return blogDao.fileInsert(fileDto);
    }

    @Override
    public int blogInsert(BlogDto blogDto) {
        return blogDao.blogInsert(blogDto);
    }

    @Override
    public List<BlogListDto> blogList(int offset) {
        return blogDao.blogList(offset);
    }

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
    @Override
    public BlogDetailDto blogDetail(int blogId) throws IOException {
        BlogDto blogDto = blogDao.blogDetail(blogId);
        UserDto userDto = userDao.findUserByUserSeq(blogDto.getUserSeq());
        List<BlogFileDto> blogFileDto = blogDao.fileList(blogId);

        int size = blogFileDto.size();
        List<String> imgList = new ArrayList<>();
        for(int i=0; i<size; i++) {
            BlogFileDto fileDto = blogFileDto.get(i);
            InputStream inputStream = new FileInputStream(uploadFolder + "/" + fileDto.getFileName());
            byte[] images = IOUtils.toByteArray(inputStream);
            byte[] byteEnc64 = Base64.encodeBase64(images);
            String imgStr = new String(byteEnc64 , "UTF-8");

            imgList.add(imgStr);
            inputStream.close();
        }

        BlogDetailDto blogDetailDto = new BlogDetailDto(userDto.getUserName(), blogDto, imgList);
        return blogDetailDto;
    }


}
