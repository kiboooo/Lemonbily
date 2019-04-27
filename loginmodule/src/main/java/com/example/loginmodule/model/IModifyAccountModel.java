package com.example.loginmodule.model;

import com.example.basemodule.bean.Account;

import java.io.File;

public interface IModifyAccountModel {

    void modifyAccount(Account account, File file);

    void modifyAccountAvatar(File file);
}
