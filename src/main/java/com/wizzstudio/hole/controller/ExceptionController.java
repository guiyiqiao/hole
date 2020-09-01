package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.exception.AuthException;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 11:13
 * @Version 1.0
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public HoleResult authException(AuthException ex){
        return HoleResult.HoleResultBuilder.HoleResult()
                .withCode(401)
                .withMessage("用户认证失败！"+ex.getMessage())
                .build();

    }
}
