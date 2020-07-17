package com.wizzstudio.hole.controller;


import com.wizzstudio.hole.util.HoleResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * 6.时间轴 ：我发布的心事、我发出的回声、我收到的回声
 * 7.每日寄语手账
 */
@RestController
@RequestMapping("hole")
public class HoleController {


    /**
     * 入住树洞有声
     * @param code 微信小程序code
     * @param nickName 昵称，默认为微信昵称
     * @return
     */
    public HoleResult enroll(String code,String nickName){
        return null;
    }

}
