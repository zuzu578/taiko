package com.taiko.taikoproject.taiko.controller;

import java.io.IOException;

import com.taiko.taikoproject.taiko.taikoutils.TaikoCrawller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaikoSongController {

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
