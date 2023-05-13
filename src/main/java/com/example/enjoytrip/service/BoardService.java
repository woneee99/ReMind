package com.example.enjoytrip.service;

import com.example.enjoytrip.dto.BoardDto;
import com.example.enjoytrip.dto.BoardParamDto;
import com.example.enjoytrip.dto.BoardResultDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
    BoardResultDto insertBoard(BoardDto boardDto, MultipartHttpServletRequest request);

    BoardResultDto getBoardList(BoardParamDto boardParamDto);

    BoardResultDto getBoardListByTitle(BoardParamDto boardParamDto);

    BoardResultDto getBoardDetail(BoardParamDto boardParamDto);

    BoardResultDto updateBoard(BoardDto boardDto, MultipartHttpServletRequest request);

    BoardResultDto deleteBoard(int boardId);
}
