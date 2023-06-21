package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BlogDetailDto;
import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogFileDto;
import com.example.enjoytrip.api.dto.BlogListDto;


import java.io.IOException;
import java.util.List;

public interface BlogService {
    int fileInsert(BlogFileDto fileDto);
    int blogInsert(BlogDto blogDto);
    List<BlogListDto> blogList(int offset);
    BlogDetailDto blogDetail(int blogId) throws IOException;
    int blogCount();
}
