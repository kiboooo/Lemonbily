package com.example.loginmodule.model.bean;

public class Login {
    private String loginID;
    private String passWord;
    private String phone;

    public Login() {
    }

    public Login(String loginID, String passWord, String phone) {
        this.loginID = loginID;
        this.passWord = passWord;
        this.phone = phone;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
