package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BoardParamDto;
import com.example.enjoytrip.api.dto.BoardResultDto;
import com.example.enjoytrip.api.dto.BoardDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
    BoardResultDto insertBoard(BoardDto boardDto, MultipartHttpServletRequest request);

    BoardResultDto getBoardList(BoardParamDto boardParamDto);

    BoardResultDto getBoardListByTitle(BoardParamDto boardParamDto);

    BoardResultDto getBoardDetail(BoardParamDto boardParamDto);

    BoardResultDto updateBoard(BoardDto boardDto, MultipartHttpServletRequest request);

    BoardResultDto deleteBoard(int boardId);
}
