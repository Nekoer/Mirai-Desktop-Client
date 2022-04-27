package com.hcyacg.miraipc.entity;


import java.io.Serializable;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/2 14:36
 */
public class Setting implements Serializable {

    private static final long serialVersionUID = 6336434320883779273L;
    private Account account;

    public Setting() {
    }

    public Setting(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "account=" + account +
                '}';
    }
}
