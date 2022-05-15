package com.taiko.taikoproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "taiko_board_file") // table name
public class TaikoBoardFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int file_no;
    private String file_path;
    private String file_name;

    public int getFile_no() {
        return file_no;
    }

    public void setFile_no(int file_no) {
        this.file_no = file_no;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

}
