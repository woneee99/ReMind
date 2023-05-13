package com.example.enjoytrip.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardResultDto {
    private BoardDto board;
    private List<BoardDto> boardList;
    private int result; // 데이터 획득 관련 결과
    private int count; // 총 건수

}
