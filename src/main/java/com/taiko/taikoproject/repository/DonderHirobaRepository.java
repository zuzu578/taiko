package com.taiko.taikoproject.repository;

import java.util.Optional;

import com.taiko.taikoproject.entity.DonderHirobaEntity;
import com.taiko.taikoproject.taikoVO.DonderHirobaLoginParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonderHirobaRepository extends JpaRepository<DonderHirobaEntity, Integer> {
    Optional<DonderHirobaEntity> findByuserMailAndUserPassword(String userMail, String userPassword);

}
