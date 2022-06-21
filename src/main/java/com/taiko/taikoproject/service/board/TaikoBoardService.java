package com.taiko.taikoproject.service.board;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.taiko.taikoproject.entity.QTaikoBoardEntity;
import com.taiko.taikoproject.entity.TaikoBoardEntity;

@Service
public class TaikoBoardService {

    @PersistenceContext
    EntityManager entityManager;

    public List<TaikoBoardEntity> getBoardList() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QTaikoBoardEntity board = new QTaikoBoardEntity("board");

        List<TaikoBoardEntity> result = queryFactory
                .select(board)
                .from(board)
                .where(board.deletedTime.eq(""))
                .orderBy(board.createdTime.asc())
                .limit(0)
                .offset(10).fetch();

        return result;
    }

}
