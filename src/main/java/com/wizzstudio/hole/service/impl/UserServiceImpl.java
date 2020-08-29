package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 15:16
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByOpenId(String openId) {
        return userMapper.queryUserByOpenId(openId);
    }

    /**
     * 修改昵称
     * @param user
     * @return
     */
    @Override
    public HoleResult updateNickname(User user) {
        int ret = userMapper.updateByPrimaryKeySelective(user);
        return ret > 0? HoleResult.success():HoleResult.failure();
    }

    /**
     * 需求四 查询用户信息
     * @param userId
     * @return
     */
    @Override
    public HoleResult getUserInfo(Integer userId) {
        return HoleResult.success(userMapper.selectByPrimaryKey(userId));
    }
}
