package com.wizzstudio.hole.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author 桂乙侨
 * @Date 2020/7/17 11:21
 * @Version 1.0
 */
@Table(name = "user")
public class User implements Serializable {

    @Id
    private Integer id;

    //微信小程序唯一id
    private String openId;

    private String nickname;

    private Boolean valid;




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
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
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
                ", nickName='" + nickname + '\'' +
                ", valid=" + valid +
                '}';
    }

    public static final class UserBuilder {
        private Integer id;
        //微信小程序唯一id
        private String openId;
        private String nickname;
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

        public UserBuilder withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder withValid(Boolean valid) {
            this.valid = valid;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setOpenId(openId);
            user.setNickname(nickname);
            user.setValid(valid);
            return user;
        }
    }
}
