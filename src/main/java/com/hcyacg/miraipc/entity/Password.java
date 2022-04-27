package com.hcyacg.miraipc.entity;


import java.io.Serializable;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/2 15:25
 */
public class Password implements Serializable {


    private static final long serialVersionUID = 4649339642750870424L;
//    private String kind;
    private String value;

    public Password(String value) {
//        this.kind = kind;
        this.value = value;
    }

    public Password() {
    }




    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Password{" +
                "value='" + value + '\'' +
                '}';
    }
}
