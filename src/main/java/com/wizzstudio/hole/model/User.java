package com.wizzstudio.hole.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:21
 * @Version 1.0
 */
public class User {

    private Integer id;

    //微信小程序唯一id
    private String openId;

    private Integer address;

    private String nickName;

    private Boolean valid;





    public static final class UserBuilder {
        private Integer id;
        private String openId;
        private Integer address;
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

        public UserBuilder withAddress(Integer address) {
            this.address = address;
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
            user.address = this.address;
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

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
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
                ", address=" + address +
                ", nickName='" + nickName + '\'' +
                ", valid=" + valid +
                '}';
    }
}
