package com.mysite.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mysite.blog.model.dto.BoardEntity;
import com.mysite.blog.service.BoardService;

import java.util.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/all")
    public List<BoardEntity> selectBoardAll() {
        List<BoardEntity> boardList = boardService.selectBoardAll();
        return boardList;
    }

    @GetMapping("/select")
    public List<BoardEntity> selectBoard(@ModelAttribute BoardEntity boardEntity) {
        List<BoardEntity> boardList = boardService.selectBoard(boardEntity);
        return boardList;
    }

    @PostMapping("/insert")
    public String insertBoard(@RequestBody BoardEntity boardEntity) {
        boardService.insertBoard(boardEntity);
        return "Insert Success";
    }

    @PostMapping("/update")
    public String updateBoard(@RequestBody BoardEntity boardEntity) {
        boardService.updateBoard(boardEntity);
        return "Update Success";
    }

    @PostMapping("/delete")
    public String deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
        return "Delete Success";
    }
}
