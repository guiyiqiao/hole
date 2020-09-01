package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
     * 需求四 我的 获取用户信息
     * @param request
     * @return
     */
    //@GetMapping
    @UserLogin
    public HoleResult getUserInfo(HttpServletRequest request){
        return  userService.getUserInfo(HoleUtils.getUserId(request));
    }

    /**
     * 需求四 修改昵称
     * @param nickname
     * @param request
     * @return
     */

    @UserLogin
    @PutMapping
    public HoleResult updateNickname(@RequestParam("nickname") String nickname,
                                     HttpServletRequest request){
        if(StringUtils.isEmpty(nickname))
            return HoleResult.failure("昵称不能为空");
        User user = new User();
        user.setNickname(nickname);
        user.setId(HoleUtils.getUserId(request));
        return userService.updateNickname(user);
    }
}
