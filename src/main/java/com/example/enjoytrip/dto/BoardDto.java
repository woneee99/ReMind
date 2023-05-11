package com.example.enjoytrip.dto;

import lombok.*;

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
    private String createdAt;
    private String updatedAt;
}
