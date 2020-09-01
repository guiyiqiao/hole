package com.wizzstudio.hole.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 桂乙侨
 * @Date 2020/8/30 15:50
 * @Version 1.0
 */
public class HoleUtils {

    public static String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return token;
    }

    /**
     * valid 验证参数错误信息返回工具
     * @param bindingResult
     * @return
     */
    public static HoleResult valid(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                builder.append(fieldError.getDefaultMessage());
                builder.append("  ");

            });
            return HoleResult.failure(builder.toString());
        }
        return null;
    }

    public static Integer getUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return TokenUtil.getUserId(token);
    }
}
