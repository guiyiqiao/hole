package com.wizzstudio.hole.mapper;


import com.wizzstudio.hole.model.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User queryUserByOpenId(String openId);

    User queryUserById(Integer id);

    int insertUser(User user);
}
