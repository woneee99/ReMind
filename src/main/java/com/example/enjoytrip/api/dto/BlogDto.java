package com.example.enjoytrip.api.dto;

import com.example.enjoytrip.api.entity.BlogFile;
import com.example.enjoytrip.api.entity.HashTag;
import com.example.enjoytrip.api.entity.Location;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private int blogId;
    private String content;
    private int likeCount;
    private LocalDateTime createdAt;

    // 블로그 사진 url list형식
    private List<BlogFile> fileList;

    private int userSeq;
    private String userName;
    private String profileImageUrl;

    private List<HashTag> hashTag;
//    private Location location;
}
