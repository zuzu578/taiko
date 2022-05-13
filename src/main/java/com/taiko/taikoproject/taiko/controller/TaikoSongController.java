package com.taiko.taikoproject.taiko.controller;

import java.util.List;

import java.util.Optional;

import com.taiko.taikoproject.entity.TaikoSongListEntity;
import com.taiko.taikoproject.repository.TaikoSongListRepository;
import com.taiko.taikoproject.taiko.taikoutils.TaikoCrawller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaikoSongController {

    @Autowired
    TaikoCrawller crawller;

    @Autowired
    TaikoSongListRepository taiko;

    Optional<TaikoSongListEntity> entity;

    @GetMapping("/crawlling")
    public String crawlling() {

        // try {
        // crawller.startCrawling();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        return "success";
    }

    @GetMapping("/songList")
    public List<TaikoSongListEntity> getSongList(String genre) {

        List<TaikoSongListEntity> songList = taiko.findAll();

        return songList;
    }
}
