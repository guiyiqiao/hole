package com.wizzstudio.hole.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:15
 * @Version 1.0
 * 返回给前端数据响应的统一封装格式
 */
@ApiModel(description = "同一返回结果集格式")
public class HoleResult implements Serializable {

    @ApiModelProperty(value = "返回码200为成功，其他为失败")
    private int code;

    @ApiModelProperty(value = "返回的提示信息")
    private String message;

    @ApiModelProperty(value = "可能返回的数据存放处")
    private Object data;

    public static HoleResult success(){
        return new HoleResult(200,"操作成功");
    }

    public static HoleResult failure(){
        return new HoleResult(600,"操作失败");
    }
    public static HoleResult success(Object data){
        return new HoleResult(200,"操作成功",data);
    }

    public static HoleResult failure(String message){
        return new HoleResult(600,message);
    }








    public HoleResult() {
    }

    public HoleResult(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public HoleResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HoleResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


    public static final class HoleResultBuilder {
        private int code;
        private String message;
        private Object data;

        public HoleResultBuilder() {
        }

        public static HoleResultBuilder HoleResult() {
            return new HoleResultBuilder();
        }

        public HoleResultBuilder withCode(int code) {
            this.code = code;
            return this;
        }

        public HoleResultBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public HoleResultBuilder withData(Object data) {
            this.data = data;
            return this;
        }

        public HoleResult build() {
            HoleResult holeResult = new HoleResult();
            holeResult.setCode(code);
            holeResult.setMessage(message);
            holeResult.setData(data);
            return holeResult;
        }
    }
}
