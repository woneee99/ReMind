package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.BoardFileDto;
import com.example.enjoytrip.api.dto.BoardParamDto;
import com.example.enjoytrip.api.dto.BoardResultDto;
import com.example.enjoytrip.api.dao.BoardDao;
import com.example.enjoytrip.api.dto.BoardDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Value("${app.fileUpload.uploadPath}")
    String uploadPath;

    @Value("${app.fileUpload.uploadDir}")
    String uploadFolder;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    @Transactional
    public BoardResultDto insertBoard(BoardDto boardDto, MultipartHttpServletRequest request) {
        BoardResultDto boardResultDto = new BoardResultDto();

        List<File> rollbackFileList = new ArrayList<>(); // rollback 시 서버에 저장된 파일을 삭제해야 하므로 임시 저장하는 공간

        try {
            boardDao.insertBoard(boardDto);

//			// transaction test #1
            // 테이블에만 insert 된 상태 => rollback 된다.
//			String s = null;
//			s.length();

            List<MultipartFile> fileList = request.getFiles("file"); // 게시물 첨부 파일

            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();

            uploadBoardFiles(boardDto, boardResultDto, rollbackFileList, fileList);

        } catch (Exception e) {
            e.printStackTrace();

            // 실제 저장된 file을 삭제
            for (File file : rollbackFileList) {
                if (file.exists()) {
                    file.delete();
                }
            }

            boardResultDto.setResult(FAIL);
        }
        return boardResultDto;
    }

    @Override
    public BoardResultDto getBoardList(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            List<BoardDto> list = boardDao.getBoardList(boardParamDto);
            int count = boardDao.getTotalBoardCount();
            boardResultDto.setBoardList(list);
            boardResultDto.setCount(count);
            boardResultDto.setResult(SUCCESS);

        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto getBoardListByTitle(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            List<BoardDto> list = boardDao.getBoardListByTitle(boardParamDto);
            int count = boardDao.getTotalBoardCountByTitle(boardParamDto);

            boardResultDto.setBoardList(list);
            boardResultDto.setCount(count);

            boardResultDto.setResult(SUCCESS);

        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }

        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto getBoardDetail(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            int userReadCnt = boardDao.getViewsCount(boardParamDto);
            if( userReadCnt == 0 ) {
                boardDao.insertFirstView(boardParamDto.getBoardId(), boardParamDto.getUserId());
                boardDao.increaseViewCount(boardParamDto.getBoardId());
            }

            BoardDto boardDto = boardDao.getBoardDetail(boardParamDto);
            List<BoardFileDto> fileList = boardDao.getAttachedFiles(boardDto.getBoardId());

            boardDto.setFileList(fileList);
            boardResultDto.setBoard(boardDto);

            boardResultDto.setResult(SUCCESS);

        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }

        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto updateBoard(BoardDto boardDto, MultipartHttpServletRequest request) {
        BoardResultDto boardResultDto = new BoardResultDto();

        // rollback 할 때 물리적으로 저장된 파일도 삭제하기 위해
        List<File> rollbackFileList = new ArrayList<>();

        try {
            boardDao.updateBoard(boardDto);

            List<MultipartFile> fileList = request.getFiles("file");

            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();

            List<String> fileUrlList = boardDao.getAttachedFileUrls(boardDto.getBoardId());
            for(String fileUrl : fileUrlList) {
                File file = new File(uploadPath + File.separator, fileUrl);
                if(file.exists()) {
                    file.delete();
                }
            }

            // 첨부 파일 delete & insert 수행
            boardDao.deleteAttachedFiles(boardDto.getBoardId());
            uploadBoardFiles(boardDto, boardResultDto, rollbackFileList, fileList);

        }catch(Exception e) {
            e.printStackTrace();

            // 실제 저장된 file을 삭제
            for(File file : rollbackFileList) {
                if(file.exists()) {
                    file.delete();
                }
            }

            boardResultDto.setResult(FAIL);
        }

        return boardResultDto;
    }

    @Override
    @Transactional
    public BoardResultDto deleteBoard(int boardId) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try {
            List<String> fileUrlList = boardDao.getAttachedFileUrls(boardId);
            for(String fileUrl : fileUrlList) {
                File file = new File(uploadPath + File.separator, fileUrl);
                if(file.exists()) {
                    file.delete();
                }
            }

            boardDao.deleteAttachedFiles(boardId);
            boardDao.deleteFirstView(boardId);
            boardDao.deleteBoard(boardId);

            boardResultDto.setResult(SUCCESS);

        }catch(Exception e) {
            e.printStackTrace();
            boardResultDto.setResult(FAIL);
        }

        return boardResultDto;
    }

    private void uploadBoardFiles(BoardDto boardDto, BoardResultDto boardResultDto, List<File> rollbackFileList, List<MultipartFile> fileList) throws IOException {
        for (MultipartFile file : fileList) {
            int boardId = boardDto.getBoardId();

            String fileName = file.getOriginalFilename();

            UUID uuid = UUID.randomUUID();
            String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()

            String savingFileName = uuid + "." + extension;

            // 파일명을 UUID 로 변환하여 저장 -> 파일명 중복 방지
            File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

            rollbackFileList.add(destFile);

            file.transferTo(destFile);

            // Table Insert
            BoardFileDto boardFileDto = new BoardFileDto();
            boardFileDto.setBoardId(boardId);
            boardFileDto.setFileName(fileName);
            boardFileDto.setFileSize(file.getSize());
            boardFileDto.setFileContentType(file.getContentType());
            String boardFileUrl = uploadFolder + "/" + savingFileName;
            boardFileDto.setFileUrl(boardFileUrl);

            boardDao.insertAttachedFiles(boardFileDto);
        }

        boardResultDto.setResult(SUCCESS);
    }
}
