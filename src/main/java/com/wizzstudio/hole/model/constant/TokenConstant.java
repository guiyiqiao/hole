package com.wizzstudio.hole.model.constant;

/**
 *
 * 登陆认证使用到的 TOKEN REFRESH_TOKEN 前缀常量
 * @Author 桂乙侨
 * @Date 2020/7/20 17:00
 * @Version 1.0
 */
public enum TokenConstant {

    USER_TOKEN("hole::user::token::"),USER_REFRESH_TOKEN("hole::user::refreshToken");

    private String value;

    TokenConstant(String s) {
        this.value = s;
    }
}
