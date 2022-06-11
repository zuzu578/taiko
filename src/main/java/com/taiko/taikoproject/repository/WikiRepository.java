package com.taiko.taikoproject.repository;

import java.util.HashMap;

import com.taiko.taikoproject.entity.WikiEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WikiRepository extends JpaRepository<WikiEntity, String> {

    @Query(value = "SELECT v FROM taiko_wiki v WHERE v.title Like :title%")
    WikiEntity findBytitleContaining(@Param("title") String title);

}
