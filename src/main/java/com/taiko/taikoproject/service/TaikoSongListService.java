package com.taiko.taikoproject.service;

import com.taiko.taikoproject.repository.TaikoSongListRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class TaikoSongListService {

    @Autowired
    private TaikoSongListRepository taikoRepository;

}
