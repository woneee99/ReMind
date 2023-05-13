package com.example.enjoytrip.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private int boardId;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int readCount;
    private boolean sameUser;
    private List<BoardFileDto> fileList;
}
