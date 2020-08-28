package com.wizzstudio.hole.service;


import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.util.HoleResult;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 15:15
 * @Version 1.0
 */
public interface UserService {
    User getUserByOpenId(String openId);

    HoleResult getUserInfo(Integer userId);
}
