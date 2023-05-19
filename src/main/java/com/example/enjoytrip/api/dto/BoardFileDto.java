package com.example.enjoytrip.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDto {
    private int fileId;
    private int boardId;
    private String fileName;
    private long fileSize;
    private String fileContentType;
    private String fileUrl;
    private LocalDateTime createdAt;
}
