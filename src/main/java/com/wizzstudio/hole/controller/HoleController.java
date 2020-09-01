package com.wizzstudio.hole.controller;


import com.wizzstudio.hole.annotation.PassToken;
import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.service.TimelineService;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 18:48
 * @Version 1.0
 */

/**
 * 需求分析
 * 1.入住树洞
 * 2.添加心事
 * 3.响应心事。评论
 * 4.查询我的心事
 * 5.查询我回应的心事
 * 6.时间轴 ：我发布的心事、我发出的回声(评论)、我收到的回声
 * 7.每日寄语手账(与后端无关）
 */
@RestController
@RequestMapping("hole")
public class HoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private TimelineService timelineService;
    /**
     * 需求6.时间轴
     * @param request
     * @return
     */
    //@GetMapping("timeline")
    @UserLogin
    public HoleResult timeline(HttpServletRequest request){
        return timelineService.listTimeline(HoleUtils.getUserId(request));
    }


    /**
     * 需求1.入住树洞有声,注册/登陆
     * @param code 微信小程序code
     * @param nickName 昵称，默认为微信昵称
     * @return
     */
    //@PostMapping("login")
    @PassToken
    public HoleResult login(HttpServletRequest request,HttpServletResponse response, String code, String nickName){
        if(StringUtils.isEmpty(code)){
            HoleResult.failure("网络异常，请稍后重试！");
        }
        return userService.login(request,response,code,nickName);
    }


}
