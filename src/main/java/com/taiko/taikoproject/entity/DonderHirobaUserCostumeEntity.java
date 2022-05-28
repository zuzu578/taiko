package com.taiko.taikoproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_hiroba_costume") // table name
public class DonderHirobaUserCostumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private int userIdx;
    private String costumeUrl;
    private String costumeType;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getCostumeUrl() {
        return costumeUrl;
    }

    public void setCostumeUrl(String costumeUrl) {
        this.costumeUrl = costumeUrl;
    }

    public String getCostumeType() {
        return costumeType;
    }

    public void setCostumeType(String costumeType) {
        this.costumeType = costumeType;
    }

}
