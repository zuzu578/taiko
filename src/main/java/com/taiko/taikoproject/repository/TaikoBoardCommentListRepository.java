package com.taiko.taikoproject.repository;

import java.util.List;

import com.taiko.taikoproject.entity.TaikoBoardCommentListEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaikoBoardCommentListRepository extends JpaRepository<TaikoBoardCommentListEntity, Long> {
    List<TaikoBoardCommentListEntity> findCommentListByBoardNo(int boardNo, final Pageable pageable);

    List<TaikoBoardCommentListEntity> findByboardNoAndDeletedTimeNull(Pageable result, int boardNo);

}
