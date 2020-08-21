package com.wizzstudio.hole.model.wx;

import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/7/19 18:48
 * @Version 1.0
 */
public class JWTToken implements Serializable {
    private static final long serialVersionUID = -8451637096112402805L;
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}