package com.taiko.taikoproject.taiko.controller.Wiki;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class WikiController {

    @PostMapping("/postingWiki")
    public void writeWiki(@RequestBody SomeEnityData entity) {
        // TODO: process POST request

        return entity;
    }

}
