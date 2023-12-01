package com.mysite.blog.service;

import com.mysite.blog.model.dto.BoardEntity;
import com.mysite.blog.model.repository.IBoardMapper;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private IBoardMapper boardMapper;

    public List<BoardEntity> selectBoardAll() {
        List<BoardEntity> boardList = boardMapper.selectBoardAll();
        return boardList;
    }
    
    public List<BoardEntity> selectBoard(BoardEntity boardEntity) {
        List<BoardEntity> boardList = boardMapper.selectBoard(boardEntity);
        return boardList;
    }

    public void insertBoard(BoardEntity boardEntity) {
        if (boardEntity.getBoardTitle() != null
            | boardEntity.getBoardContent() != null) {
            boardMapper.insertBoard(boardEntity);
        }
    }

    public void updateBoard(BoardEntity boardEntity) {
        if (boardEntity.getBoardId() != 0) {
            boardMapper.updateBoard(boardEntity);
        }
    }

    public void deleteBoard(int boardId) {
        if (boardId != 0){
            boardMapper.deleteBoard(boardId);
        }
    }
}
