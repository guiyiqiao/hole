package com.wizzstudio.hole.model.constant;

/**
 * @Author 桂乙侨
 * @Date 2020/8/26 20:59
 * @Version 1.0
 */
public enum  CacheKey {
    BLOG_HUG_PREFIX("hole::blog::hug"),
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

    public static String getBlogHugSetKey(){
        return BLOG_HUG_PREFIX+"::set";
    }

    public static String getEchoThankSetKey(){
        return ECHO_THANK_PREFIX+"::set";
    }
}
