package com.wizzstudio.hole.service;


import com.wizzstudio.hole.model.User;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 15:15
 * @Version 1.0
 */
public interface UserService {
    User getUserByOpenId(String openId);
}
