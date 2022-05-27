package com.taiko.taikoproject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_favorite_song") // table name
public class UserFavoriteSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSongIdx;
    private int userIdx;
    private String userFavoriteSong;

    public int getUserSongIdx() {
        return userSongIdx;
    }

    public void setUserSongIdx(int userSongIdx) {
        this.userSongIdx = userSongIdx;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getUserFavoriteSong() {
        return userFavoriteSong;
    }

    public void setUserFavoriteSong(String userFavoriteSong) {
        this.userFavoriteSong = userFavoriteSong;
    }

}
