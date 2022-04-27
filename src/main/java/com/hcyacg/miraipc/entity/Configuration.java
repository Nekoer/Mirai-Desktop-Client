package com.hcyacg.miraipc.entity;


import java.io.Serializable;

/**
 * @Author: Nekoer
 * @Desc: TODO
 * @Date: 2021/9/2 15:26
 */
public class Configuration implements Serializable {

    private static final long serialVersionUID = -5875469268062482528L;
    private String protocol;

    public Configuration(String protocol) {
        this.protocol = protocol;
    }

    public Configuration() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "protocol='" + protocol + '\'' +
                '}';
    }
}
