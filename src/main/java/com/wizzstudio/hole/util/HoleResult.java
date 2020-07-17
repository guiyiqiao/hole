package com.wizzstudio.hole.util;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:15
 * @Version 1.0
 * 返回给前端数据响应的统一封装格式
 */
public class HoleResult {

    private int code;

    private String message;

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

    public static HoleResult failure(Object data){
        return new HoleResult(600,"操作失败",data);
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

}
