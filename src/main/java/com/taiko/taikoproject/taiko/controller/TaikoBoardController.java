package com.taiko.taikoproject.taiko.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.taiko.taikoproject.entity.TaikoBoardCommentListEntity;
import com.taiko.taikoproject.entity.TaikoBoardEntity;
import com.taiko.taikoproject.repository.TaikoBoardCommentListRepository;
import com.taiko.taikoproject.repository.TaikoBoardListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaikoBoardController {
    @Autowired
    TaikoBoardListRepository taikoBoard;

    @Autowired
    TaikoBoardCommentListRepository comment;

    Optional<TaikoBoardEntity> entity;

    @GetMapping("/board")
    public List<TaikoBoardEntity> getBoardList() {

        List<TaikoBoardEntity> result = taikoBoard.findAll();

        return result;

    }

    @GetMapping("/boardComment")
    public List<TaikoBoardCommentListEntity> getBoardComment(HttpServletRequest req) {
        String boardNo = req.getParameter("boardNo");

        List<TaikoBoardCommentListEntity> result = comment.findCommentListByBoardNo(Integer.parseInt(boardNo));
        return result;
    }

}
