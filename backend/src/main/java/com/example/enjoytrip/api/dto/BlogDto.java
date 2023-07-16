package com.example.enjoytrip.api.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private int blogId;
    private int userSeq;
    private int planId;
    private int tripPlanSpotId;
    private String content;
    private String hashTag;
    private LocalDateTime createdAt;

    private List<MultipartFile> fileList;
    private List<BlogFileDto> fileDto;
}
