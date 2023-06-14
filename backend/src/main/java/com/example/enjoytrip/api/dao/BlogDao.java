package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDao {
    int fileInsert(BlogFileDto fileDto);
    int blogInsert(BlogDto blogDto);
    List<BlogListDto> blogList(int offset);
    BlogDto blogDetail(int blogId);
    List<BlogFileDto> fileList(int blogId);
}
