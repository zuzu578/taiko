package com.taiko.taikoproject.taikoVO;

public class TaikoVO {
    private int songNo;
    private String songName;
    private String songGenre;
    private String difficulty;

    public int getSongNo() {
        return songNo;
    }

    public void setSongNo(int songNo) {
        this.songNo = songNo;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
