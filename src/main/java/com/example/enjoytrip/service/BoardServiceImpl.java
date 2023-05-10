package com.example.enjoytrip.service;

import com.example.enjoytrip.dao.BoardDao;
import com.example.enjoytrip.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardDao dao;

    @Override
    public List<BoardDto> getBoardList() {
        return dao.getBoardList();
    }
}
