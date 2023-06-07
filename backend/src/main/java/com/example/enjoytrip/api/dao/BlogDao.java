package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogFileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogDao {
    int fileInsert(BlogFileDto fileDto);
    int blogInsert(BlogDto blogDto);
}
