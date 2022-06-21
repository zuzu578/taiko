package com.taiko.taikoproject.service.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taiko.taikoproject.entity.TaikoBoardEntity;

@Service
public class TaikoBoardService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TaikoBoardService taikoBoardService;

    public List<TaikoBoardEntity> boardList() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        return null;
    }

}
