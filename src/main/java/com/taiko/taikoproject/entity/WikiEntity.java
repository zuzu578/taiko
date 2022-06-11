package com.taiko.taikoproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "taiko_wiki") // table name
public class WikiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String ip_address;
    private String title;
    private String subtitle;
    private String song_writer;
    private String sheet_maker;
    private String recorded;
    private String kantan;
    private String hutsuu;
    private String muzukashi;
    private String oni;
    private String ura;
    private String text;
    private String created_time;
    private String delete_time;
    private String updated_time;
    private String thumnail;

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSong_writer() {
        return song_writer;
    }

    public void setSong_writer(String song_writer) {
        this.song_writer = song_writer;
    }

    public String getSheet_maker() {
        return sheet_maker;
    }

    public void setSheet_maker(String sheet_maker) {
        this.sheet_maker = sheet_maker;
    }

    public String getRecorded() {
        return recorded;
    }

    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    public String getKantan() {
        return kantan;
    }

    public void setKantan(String kantan) {
        this.kantan = kantan;
    }

    public String getHutsuu() {
        return hutsuu;
    }

    public void setHutsuu(String hutsuu) {
        this.hutsuu = hutsuu;
    }

    public String getMuzukashi() {
        return muzukashi;
    }

    public void setMuzukashi(String muzukashi) {
        this.muzukashi = muzukashi;
    }

    public String getOni() {
        return oni;
    }

    public void setOni(String oni) {
        this.oni = oni;
    }

    public String getUra() {
        return ura;
    }

    public void setUra(String ura) {
        this.ura = ura;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(String delete_time) {
        this.delete_time = delete_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

}
