package com.taiko.taikoproject.repository;

import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.TaikoBoardEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaikoCRUDRepository extends JpaRepository<TaikoBoardEntity, Long> {

    Optional<TaikoBoardEntity> findByBoardNoAndPassword(int boardNo, String password);

    List<TaikoBoardListRepository> findByboardNo(int boardNo);

}
