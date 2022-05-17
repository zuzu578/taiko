package com.taiko.taikoproject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity(name = "taiko_board") // table name
public class TaikoBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNo;
    private String userName;
    private String userProfile;
    private String contents;
    private String fileNo;
    private String createdTime;
    private String deletedTime;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "fileNo", insertable = false, updatable = false)
    private TaikoBoardFileEntity file;

    public TaikoBoardFileEntity getFile() {
        return file;
    }

    public void setFile(TaikoBoardFileEntity file) {
        this.file = file;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(String deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Override
    public String toString() {
        return "TaikoBoardEntity [boardNo=" + boardNo + ", contents=" + contents + ", createdTime=" + createdTime
                + ", deletedTime=" + deletedTime + ", fileNo=" + fileNo + ", userName=" + userName + ", userProfile="
                + userProfile + "]";
    }

}
