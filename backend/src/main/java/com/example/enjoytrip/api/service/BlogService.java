package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BlogDto;
import com.example.enjoytrip.api.dto.BlogFileDto;


import java.util.List;

public interface BlogService {
    int fileInsert(BlogFileDto fileDto);
    int blogInsert(BlogDto blogDto);
}
