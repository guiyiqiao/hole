package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.UserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 桂乙侨
 * @Date 2020/8/28 9:27
 * @Version 1.0
 */
@RestController
@RequestMapping("hole/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 需求四 获取用户信息
     * @param request
     * @return
     */
    @GetMapping
    @UserLogin
    public HoleResult getUserInfo(HttpServletRequest request){
        return  userService.getUserInfo(UserIdUtil.getUserId(request));
    }
}
