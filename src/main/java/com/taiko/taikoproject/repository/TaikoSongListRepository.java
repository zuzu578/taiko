package com.taiko.taikoproject.repository;

import java.util.List;

import com.taiko.taikoproject.entity.TaikoSongListEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaikoSongListRepository extends JpaRepository<TaikoSongListEntity, Long> {
    List<TaikoSongListEntity> findBysongGenre(String songGenre);
}
