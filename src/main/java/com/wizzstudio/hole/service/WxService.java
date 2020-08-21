package com.wizzstudio.hole.service;

import com.wizzstudio.hole.util.HoleResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author 桂乙侨
 * @Date 2020/7/19 11:42
 * @Version 1.0
 */
public interface WxService {

    HoleResult wxLogin(HttpServletResponse response,String code, String nickName);
}
