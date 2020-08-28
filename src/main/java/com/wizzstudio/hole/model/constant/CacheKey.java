package com.wizzstudio.hole.model.constant;

/**
 * @Author 桂乙侨
 * @Date 2020/8/26 20:59
 * @Version 1.0
 */
public enum  CacheKey {
    BLOG_HUG_PREFIX("hole::blog::hug"),
    COMMENT_THANK_PREFIX("hole::comment::thank");

    private String value;

    private CacheKey(String value) {
        this.value = value;
    }

    public static String getBlogHugUserKey(Integer blogId){
        return BLOG_HUG_PREFIX.value+"::user::"+blogId;
    }
    public static String getBlogHugKey(Integer blogId){
        return BLOG_HUG_PREFIX.value+"::count::"+blogId;
    }


    public static String getCommentThankUserKey(Integer commentId){
        return COMMENT_THANK_PREFIX.value+"::user::"+commentId;
    }
    public static String getCommentThankKey(Integer commentId) {
        return COMMENT_THANK_PREFIX.value + "::count::" + commentId;
    }
}
