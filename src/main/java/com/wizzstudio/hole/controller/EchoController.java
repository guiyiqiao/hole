package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.PassToken;
import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.model.vo.EchoVo;
import com.wizzstudio.hole.service.EchoService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:31
 * @Version 1.0
 */
@Api(tags = "回声相关接口")
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
    @ApiOperation(value = "需要登陆；对心事 传递回声 接口")
    @UserLogin
    @PostMapping
    public HoleResult addEcho(@Valid EchoVo echoVo, BindingResult bindingResult, HttpServletRequest request){
        HoleResult holeResult = HoleUtils.valid(bindingResult);
        if(holeResult != null)
            return holeResult;
        Echo echo  = new Echo();
        BeanUtils.copyProperties(echoVo,echo);
        echo.setThank(0);
        echo.setReport(false);
        echo.setPublishTime(new Date());
        echo.setSolved(false);
        echo.setUserId(HoleUtils.getUserId(request));
        echo.setOvert(false);
        return echoService.addEcho(echo);
    }

    @PostMapping("thank")
    @PassToken
    @ApiOperation(value = "不需要登陆；对回声进行感谢")
    public HoleResult thank(@RequestParam("echoId") Integer echoId){
        return echoService.thank(echoId);
    }

    //设置echo可见
    @ApiOperation(value = "需要登陆；公开我的某一发布心事下别人给我的回声")
    @UserLogin
    @PostMapping("open")
    public HoleResult openEcho(@RequestParam("echoId") Integer echoId,
                               HttpServletRequest request){
        return echoService.openEcho(echoId, HoleUtils.getUserId(request));
    }

    /**
     * 删除echo
     * @param echoId
     * @param request
     * @return
     */
    @DeleteMapping
    @UserLogin
    @ApiOperation(value = "需要登陆；删除我的一条回声")
    public HoleResult delete(@RequestParam("echoId") Integer echoId,
                             HttpServletRequest request){
        return echoService.deleteEcho(echoId,HoleUtils.getUserId(request));
    }

    /**
     * 查询回声大厅
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "不需要登陆；查询 回声大厅 接口")
    @GetMapping("hall")
    @PassToken
    public HoleResult listOvertEcho(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("ageSize") int pageSize){
        return echoService.listOvertEcho(pageNum,pageSize);
    }



}
