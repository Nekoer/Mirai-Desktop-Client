package com.hcyacg.miraipc.entity;


import java.io.Serializable;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/2 13:49
 */

public class Account implements Serializable {

    private static final long serialVersionUID = 7780805043960933977L;
    private String account;
    private Password password;
    private Configuration configuration;

    public Account() {
    }

    public Account(String account, Password password, Configuration configuration) {
        this.account = account;
        this.password = password;
        this.configuration = configuration;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", password=" + password +
                ", configuration=" + configuration +
                '}';
    }
}
