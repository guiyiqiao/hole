package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.vo.EchoVo;
import com.wizzstudio.hole.service.EchoService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.UserIdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:31
 * @Version 1.0
 */
@RestController
@RequestMapping("hole/echo")
public class EchoController {
    @Autowired
    private EchoService echoService;

    /**
     * 需求2.传递回声
     * @param echoVo
     * @return
     */
    @UserLogin
    @PostMapping
    public HoleResult addEcho(@Valid EchoVo echoVo, HttpServletRequest request){
        Echo echo  = new Echo();
        BeanUtils.copyProperties(echo,echoVo);
        echo.setThank(0);
        echo.setReport(false);
        echo.setPublishTime(new Date());
        echo.setSolved(false);
        echo.setUserId(UserIdUtil.getUserId(request));
        return echoService.addEcho(echo);
    }

    @PostMapping("thank")
    public HoleResult thank(@RequestParam("echoId") Integer echoId){
        return echoService.thank(echoId);
    }
}
