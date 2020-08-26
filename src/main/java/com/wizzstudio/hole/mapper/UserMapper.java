package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
    User queryUserByOpenId(String openId);

    User queryUserById(Integer id);

    int insertUser(User user);
}
