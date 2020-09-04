package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 桂乙侨
 * @Date 2020/8/28 9:27
 * @Version 1.0
 */
@Api(tags = "用户相关接口")
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
    @ApiOperation(value = "需要登陆；登陆后，获取后台存储的用户信息")
    @GetMapping
    @UserLogin
    public HoleResult getUserInfo(HttpServletRequest request){
        final User userInfo = userService.getUserInfo(HoleUtils.getUserId(request));
        return userInfo != null?HoleResult.success(userInfo):HoleResult.failure();
    }

    /**
     * 需求四 修改昵称
     * @param nickname
     * @param request
     * @return
     */

    @UserLogin
    @PutMapping
    @ApiOperation(value = "需要登陆；修改后台存储的用户昵称信息")
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
