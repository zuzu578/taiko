package com.taiko.taikoproject.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_like_song") // table name
public class UserLikeSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userLikeSongIdx;
    private int userIdx;
    private String userLikeSong;

    public int getUserLikeSongIdx() {
        return userLikeSongIdx;
    }

    public void setUserLikeSongIdx(int userLikeSongIdx) {
        this.userLikeSongIdx = userLikeSongIdx;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getUserLikeSong() {
        return userLikeSong;
    }

    public void setUserLikeSong(String userLikeSong) {
        this.userLikeSong = userLikeSong;
    }

}
