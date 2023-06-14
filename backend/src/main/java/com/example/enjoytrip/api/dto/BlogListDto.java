package com.example.enjoytrip.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogListDto {
    private int blogId;
    private String userName;
    private String hashTag;
    private Date createdAt;
    private String fileName;
    private String images;
}
