package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.BoardFileDto;
import com.example.enjoytrip.api.dto.BoardParamDto;
import com.example.enjoytrip.api.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {
    // 상세
    BoardDto getBoardDetail(BoardParamDto boardParamDto);

    List<BoardFileDto> getAttachedFiles(int boardId);

    // 게시글을 어떤 사용자가 조회했었는지 확인
    int getViewsCount(BoardParamDto boardParamDto); // mapper 에서 map 파라미터

    // 신규로 게시글 조회 history 등록
    int insertFirstView(@Param("boardId") int boardId, @Param("userSeq") int userSeq); // mapper 에서 map 파라미터

    int increaseViewCount(int boardId); // 게시글 조회수 증가

    // 삭제
    int deleteBoard(int boardId);

    int deleteAttachedFiles(int boardId);

    int deleteFirstView(int boardId);

    List<String> getAttachedFileUrls(int boardId); // 첨부파일의 물리적인 저장 url 목록

    // 등록
    int insertBoard(BoardDto boardDto);

    int insertAttachedFiles(BoardFileDto boardFileDto);

    // 목록
    List<BoardDto> getBoardList(BoardParamDto boardParamDto);

    int getTotalBoardCount(); // 총 건수

    // 목록 - 검색어
    List<BoardDto> getBoardListByTitle(BoardParamDto boardParamDto);

    int getTotalBoardCountByTitle(BoardParamDto boardParamDto); // 검색어 총 건수

    // 수정
    int updateBoard(BoardDto boardDto);
    // 첨부파일은 => 단순 UI인 경우 delete - insert, 개별 첨부파일 처리는 복잡 UI (파일별 삭제 버튼)
}
