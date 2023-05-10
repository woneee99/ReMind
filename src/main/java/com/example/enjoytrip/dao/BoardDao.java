package com.example.enjoytrip.dao;

import com.example.enjoytrip.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardDto> getBoardList(); // TODO: pagination을 위한 방법 고민. 현재는 모든 게시물 불러옴
}
