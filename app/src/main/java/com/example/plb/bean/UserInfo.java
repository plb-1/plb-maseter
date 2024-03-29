package com.example.plb.bean;

/**
 * 保存用户登录的登录名 + 密码
 * Created by rose on 2019/12/16.
 */

public class UserInfo {
    private static UserInfo userInfo = null;
    private String userName = "";
    private String passWord = "";
    private String userImg = "";
    private int userId = 0;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private UserInfo(){}

    public static UserInfo getInstance(){
        if (userInfo == null) {
            synchronized (UserInfo.class){
                if (userInfo == null) {
                    userInfo = new UserInfo();
                }
            }
        }
        return userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public boolean hasUserInfo(){
        if (!"".equals(userName)&&!"".equals(passWord))
            return true;
        else
            return false;
    }
}
