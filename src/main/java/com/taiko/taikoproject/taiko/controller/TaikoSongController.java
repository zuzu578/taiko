package com.taiko.taikoproject.taiko.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.taiko.taikoproject.entity.TaikoSongListEntity;
import com.taiko.taikoproject.repository.TaikoSongListRepository;
import com.taiko.taikoproject.taiko.taikoutils.TaikoCrawller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaikoSongController {

    // @Autowired
    // TaikoSongListRepository taiko;

    Optional<TaikoSongListEntity> entxity;

    @GetMapping("/test")
    public String test() {
        TaikoCrawller crawller = new TaikoCrawller();
        try {

            crawller.startCrawling();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
