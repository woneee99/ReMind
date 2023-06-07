package com.example.enjoytrip.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {
    private int blogId;
    private int planId;
    private int tripPlanSpotId;
    private String content;
    private String hashTag;
    private List<String> fileList;
}
