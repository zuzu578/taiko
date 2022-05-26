package com.taiko.taikoproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "hiroba_user") // table name
public class DonderHirobaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;
    private String userMail;
    private String userPassword;
    private String userName;
    private String userStyle;
    private String userDanwi;
    private String userMydon;
    private String userDonmedal;
    private String userToken;
    private String userBestRank8;
    private String userBestRank7;
    private String userBestRank6;
    private String userBestRank5;
    private String userBestRank4;
    private String userBestRank3;
    private String userBestRank2;
    private String userSilverCrown;
    private String userGoldCrown;
    private String userDonderfulCrown;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStyle() {
        return userStyle;
    }

    public void setUserStyle(String userStyle) {
        this.userStyle = userStyle;
    }

    public String getUserDanwi() {
        return userDanwi;
    }

    public void setUserDanwi(String userDanwi) {
        this.userDanwi = userDanwi;
    }

    public String getUserMydon() {
        return userMydon;
    }

    public void setUserMydon(String userMydon) {
        this.userMydon = userMydon;
    }

    public String getUserDonmedal() {
        return userDonmedal;
    }

    public void setUserDonmedal(String userDonmedal) {
        this.userDonmedal = userDonmedal;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserBestRank8() {
        return userBestRank8;
    }

    public void setUserBestRank8(String userBestRank8) {
        this.userBestRank8 = userBestRank8;
    }

    public String getUserBestRank7() {
        return userBestRank7;
    }

    public void setUserBestRank7(String userBestRank7) {
        this.userBestRank7 = userBestRank7;
    }

    public String getUserBestRank6() {
        return userBestRank6;
    }

    public void setUserBestRank6(String userBestRank6) {
        this.userBestRank6 = userBestRank6;
    }

    public String getUserBestRank5() {
        return userBestRank5;
    }

    public void setUserBestRank5(String userBestRank5) {
        this.userBestRank5 = userBestRank5;
    }

    public String getUserBestRank4() {
        return userBestRank4;
    }

    public void setUserBestRank4(String userBestRank4) {
        this.userBestRank4 = userBestRank4;
    }

    public String getUserBestRank3() {
        return userBestRank3;
    }

    public void setUserBestRank3(String userBestRank3) {
        this.userBestRank3 = userBestRank3;
    }

    public String getUserBestRank2() {
        return userBestRank2;
    }

    public void setUserBestRank2(String userBestRank2) {
        this.userBestRank2 = userBestRank2;
    }

    public String getUserSilverCrown() {
        return userSilverCrown;
    }

    public void setUserSilverCrown(String userSilverCrown) {
        this.userSilverCrown = userSilverCrown;
    }

    public String getUserGoldCrown() {
        return userGoldCrown;
    }

    public void setUserGoldCrown(String userGoldCrown) {
        this.userGoldCrown = userGoldCrown;
    }

    public String getUserDonderfulCrown() {
        return userDonderfulCrown;
    }

    public void setUserDonderfulCrown(String userDonderfulCrown) {
        this.userDonderfulCrown = userDonderfulCrown;
    }

}
