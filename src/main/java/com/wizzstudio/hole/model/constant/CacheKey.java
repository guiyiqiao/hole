package com.wizzstudio.hole.model.constant;

import io.swagger.models.auth.In;

/**
 * @Author 桂乙侨
 * @Date 2020/8/26 20:59
 * @Version 1.0
 */
public enum  CacheKey {
    BLOG_HUG_PREFIX("hole::blog::hug"),
    BLOG_PREFIX("hole::blog"),
    ECHO_THANK_PREFIX("hole::echo::thank");

    private String value;

    private CacheKey(String value) {
        this.value = value;
    }

    public static String getBlogHugKey(Integer blogId){
        return BLOG_HUG_PREFIX.value+"::count::"+blogId;
    }


    public static String getEchoThankKey(Integer echoId) {
        return ECHO_THANK_PREFIX.value + "::count::" + echoId;
    }


    public static String getEchoThankSetKey(){
        return ECHO_THANK_PREFIX+"::set";
    }

    public static String getBlogKey(Integer blogId){
        return BLOG_PREFIX+"::"+blogId;
    }
}
