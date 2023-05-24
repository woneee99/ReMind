package com.example.enjoytrip.api.dto;

import com.example.enjoytrip.api.entity.BlogFile;
import lombok.*;

import java.time.LocalDateTime;
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
