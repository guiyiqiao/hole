package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.exception.HoleException;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Author 桂乙侨
 * @Date 2020/7/20 11:13
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HoleException.class)
    public HoleResult holeResult(HoleException ex){
        HoleResult holeResult = HoleResult.HoleResultBuilder.HoleResult()
                .withCode(600)
                .withMessage(ex.getMessage())
                .build();
        return holeResult;
    }

}
