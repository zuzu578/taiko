package com.taiko.taikoproject.repository;

import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.TaikoBoardEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaikoCRUDRepository extends JpaRepository<TaikoBoardEntity, Long> {

    Optional<TaikoBoardEntity> findByBoardNoAndPassword(int boardNo, String password);

    List<TaikoBoardListRepository> findByBoardNo(int boardNo);

}
