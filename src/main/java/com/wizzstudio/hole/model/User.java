package com.wizzstudio.hole.model;

import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:21
 * @Version 1.0
 */
public class User implements Serializable {

    private Integer id;

    //微信小程序唯一id
    private String openId;

    private String nickName;

    private Boolean valid;


    public static final class UserBuilder {
        private Integer id;
        private String openId;
        private String nickName;
        private Boolean valid;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder withOpenId(String openId) {
            this.openId = openId;
            return this;
        }


        public UserBuilder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.openId = this.openId;
            user.nickName = this.nickName;
            user.valid = this.valid;
            return user;
        }
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", valid=" + valid +
                '}';
    }
}
