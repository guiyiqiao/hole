package com.wizzstudio.hole.model.constant;

/**
 * @Author 桂乙侨
 * @Date 2020/8/26 20:59
 * @Version 1.0
 */
public enum  BlogConstant {
    BLOG_HUG_USER_PREFIX("hole::blog::hug");

    private String value;

    private BlogConstant(String value) {
        this.value = value;
    }

    public static String getBlogHugUserKey(Integer blogId){
        return BLOG_HUG_USER_PREFIX.value+"::"+blogId+"::";
    }
    public static String getBlogHugKey(Integer blogId){
        return BLOG_HUG_USER_PREFIX.value+"::count::"+blogId;
    }
}
