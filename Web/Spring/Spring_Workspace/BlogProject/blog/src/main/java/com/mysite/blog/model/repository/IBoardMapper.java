package com.mysite.blog.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mysite.blog.model.dto.BoardEntity;

import java.util.*;

@Mapper
public interface IBoardMapper {
    public List<BoardEntity> selectBoardAll();
    public List<BoardEntity> selectBoard(BoardEntity boardEntity);
    public void insertBoard(BoardEntity boardEntity);
    public void updateBoard(BoardEntity boardEntity);
    public void deleteBoard(@Param("boardId") int boardId);
}
