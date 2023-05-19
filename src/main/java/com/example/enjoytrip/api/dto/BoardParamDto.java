package com.example.enjoytrip.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardParamDto {
    private int limit;
    private int offset;
    private String searchWord;

    private int boardId;
    private int userId;
}
