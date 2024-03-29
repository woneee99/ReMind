package com.example.enjoytrip.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetailDto {
    private int userSeq;
    private String content;
    private String hashTag;
    private int planId;
    private LocalDateTime createdAt;
    private String fileName;
    private List<String> images = new ArrayList<>();

    public BlogDetailDto(BlogDto blogDto, List<String> imgList) {
        this.userSeq = blogDto.getUserSeq();
        this.content = blogDto.getContent();
        this.hashTag = blogDto.getHashTag();
        this.planId = blogDto.getPlanId();
        this.createdAt = blogDto.getCreatedAt();
        this.images = imgList;
    }
}
