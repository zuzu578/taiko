package com.taiko.taikoproject.taiko.controller;

import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.TaikoBoardEntity;
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

    Optional<TaikoBoardEntity> entity;

    @GetMapping("/board")
    public List<TaikoBoardEntity> getBoardList() {

        List<TaikoBoardEntity> result = taikoBoard.findAll();

        return result;

    }

}
