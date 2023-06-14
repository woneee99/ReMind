package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.BlogDao;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogListDto;
import com.example.enjoytrip.api.dto.BlogFileDto;
import com.example.enjoytrip.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
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
    public BlogDto blogDetail(int blogId) throws IOException {
        BlogDto blogDto = blogDao.blogDetail(blogId);

        List<BlogFileDto> fileList = blogDao.fileList(blogId);
        System.out.println("fileList.sz = " + fileList.size());

        List<BlogFileDto> list = new ArrayList<>();
        for(BlogFileDto bf : fileList) {
            InputStream inputStream = new FileInputStream(uploadFolder + "/" + bf.getFileName());
            byte[] images = IOUtils.toByteArray(inputStream);
            bf.setImages(images);
            inputStream.close();
            list.add(bf);
        }
        blogDto.setFileDto(list);
        return blogDto;
    }


}
