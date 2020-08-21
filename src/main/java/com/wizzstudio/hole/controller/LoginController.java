package com.wizzstudio.hole.controller;


import com.wizzstudio.hole.annotation.PassToken;
import com.wizzstudio.hole.service.WxService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author 桂乙侨
 * @Date 2020/7/19 11:39
 * @Version 1.0
 */
@RestController
@RequestMapping("hole")
public class LoginController {

    @Autowired
    private WxService wxService;

    /**
     * 入住树洞有声,注册/登陆
     * @param code 微信小程序code
     * @param nickName 昵称，默认为微信昵称
     * @return
     */
    @PostMapping("login")
    @PassToken
    public HoleResult login(HttpServletResponse response,String code, String nickName){

        return wxService.wxLogin(response,code,nickName);
    }


}
