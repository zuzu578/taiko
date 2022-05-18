package com.taiko.taikoproject.repository;

import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.TaikoBoardCommentListEntity;
import com.taiko.taikoproject.entity.TaikoBoardEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaikoBoardListRepository extends JpaRepository<TaikoBoardEntity, Pageable> {

    Optional<TaikoBoardEntity> findByboardNoAndPassword(int boardNo, String password);

    List<TaikoBoardEntity> findBydeletedTimeNull(Pageable result);

}
