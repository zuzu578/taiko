package com.taiko.taikoproject.repository;

import com.taiko.taikoproject.entity.TaikoBoardEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaikoBoardListRepository extends JpaRepository<TaikoBoardEntity, Pageable> {

}
