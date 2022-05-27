package com.taiko.taikoproject.repository;

import java.util.List;
import java.util.Optional;

import com.taiko.taikoproject.entity.UserFavoriteSongEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteSongRepository extends JpaRepository<UserFavoriteSongEntity, Integer> {

    Optional<UserFavoriteSongEntity> findByuserIdx(int userIdx);

    void deleteByuserIdx(int userIdx);

}
