package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.BoardParamDto;
import com.example.enjoytrip.api.dto.BoardResultDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.service.BoardService;
import com.example.enjoytrip.api.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    @Autowired
    BoardService boardService;

    private static final int SUCCESS = 1;

    @GetMapping("")
    public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto) {

        BoardResultDto boardResultDto;
//        boardResultDto = boardParamDto.getSearchWord().isEmpty() ? boardService.getBoardList(boardParamDto) : boardService.getBoardListByTitle(boardParamDto);

        if( boardParamDto.getSearchWord().isEmpty() ) {
            boardResultDto = boardService.getBoardList(boardParamDto);
        }else {
            boardResultDto = boardService.getBoardListByTitle(boardParamDto);
        }

        if (boardResultDto.getResult() == SUCCESS) {
            return ResponseEntity.ok().body(boardResultDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session) {

        BoardParamDto boardParamDto = new BoardParamDto();
        boardParamDto.setUserId(((UserDto) session.getAttribute("userDto")).getUserSeq());
        boardParamDto.setBoardId(boardId);

        BoardResultDto boardResultDto = boardService.getBoardDetail(boardParamDto);

        // 게시글 작성자와 현 사용자가 동일
        if (((UserDto) session.getAttribute("userDto")).getUserSeq() == boardResultDto.getBoard().getUserId()) {
            boardResultDto.getBoard().setSameUser(true);
        }

        if (boardResultDto.getResult() == SUCCESS) {
            return ResponseEntity.ok().body(boardResultDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<BoardResultDto> boardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {

        boardDto.setUserId(((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
        BoardResultDto boardResultDto = boardService.insertBoard(boardDto, request);

        if (boardResultDto.getResult() == SUCCESS) {
            return ResponseEntity.ok().body(boardResultDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT + multipart/form-data (X)
    // In RESTful,
    // PUT & DELETE methods are defined to be idempotent
    // 예를 들어 하나의 게시물에 3개의 첨부파일을 넣었고, put 요청을 하려면 3개의 첨부파일이라는 개수는 고정되어야 한다.(멱등성)
    // 하지만, 그와 관계 없이도 PUT + multipart/form-data 자체가 불가능하다.
    @PostMapping("/{boardId}")
    public ResponseEntity<BoardResultDto> boardUpdate(BoardDto boardDto, MultipartHttpServletRequest request) {

        boardDto.setUserId(((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
        BoardResultDto boardResultDto = boardService.updateBoard(boardDto, request);

        if (boardResultDto.getResult() == SUCCESS) {
            return ResponseEntity.ok().body(boardResultDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<BoardResultDto> boardDelete(@PathVariable("boardId") int boardId) {
        BoardResultDto boardResultDto = boardService.deleteBoard(boardId);

        if (boardResultDto.getResult() == SUCCESS) {
            return ResponseEntity.ok().body(boardResultDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
