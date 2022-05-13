package com.taiko.taikoproject.service;

import java.util.List;

import com.taiko.taikoproject.entity.TaikoSongListEntity;
import com.taiko.taikoproject.repository.TaikoSongListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaikoSongListService {

    @Autowired
    private TaikoSongListRepository taikoRepository;

    public List<TaikoSongListEntity> findAll() {
        return taikoRepository.findAll();
    }

}
