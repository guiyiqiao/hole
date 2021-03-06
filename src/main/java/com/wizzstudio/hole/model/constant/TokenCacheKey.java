package com.wizzstudio.hole.model.constant;

/**
 *
 * 登陆认证使用到的 TOKEN REFRESH_TOKEN 前缀常量
 * @Author 桂乙侨
 * @Date 2020/7/20 17:00
 * @Version 1.0
 */
public enum TokenCacheKey {

    USER_TOKEN("hole::user::token::"),
    USER_REFRESH_TOKEN("hole::user::refreshToken::");


    private String value;

    TokenCacheKey(String s) {
        this.value = s;
    }


    public static String getUserTokenKey(Integer userId){
        return USER_TOKEN.value+userId;
    }
    public static String getUserRefreshTokenKey(Integer userId){
        return USER_REFRESH_TOKEN.value+userId;
    }
}
