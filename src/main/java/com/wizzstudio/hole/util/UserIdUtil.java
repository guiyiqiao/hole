package com.wizzstudio.hole.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 桂乙侨
 * @Date 2020/8/27 11:58
 * @Version 1.0
 */
public class UserIdUtil {

    public static Integer getUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return TokenUtil.getUserId(token);
    }
}
