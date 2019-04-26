package com.example.loginmodule.model;

import com.example.basemodule.model.IBaseModel;

public interface ILoginModel extends IBaseModel {
    void AccountLogin(String phone, String passWord);
}
