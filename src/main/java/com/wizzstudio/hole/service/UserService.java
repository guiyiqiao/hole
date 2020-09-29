package com.wizzstudio.hole.service;


import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.util.HoleResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 15:15
 * @Version 1.0
 */
public interface UserService {

    HoleResult updateNickname(User user);

    User getUserInfo(Integer userId);

    HoleResult login(HttpServletRequest request, HttpServletResponse response, String code, String nickName);
}
