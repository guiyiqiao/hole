package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.model.constant.TokenCacheKey;
import com.wizzstudio.hole.service.UserService;
import com.wizzstudio.hole.service.WxService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.HoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 桂乙侨
 * @Date 2020/7/20 15:16
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private WxService wxService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public HoleResult login(HttpServletRequest request, HttpServletResponse response, String code, String nickName) {
        String token = HoleUtils.getToken(request);
        if(token != null){
            //存在token，已经登陆过了，与缓存的token比对后放行
            String o = (String) redisTemplate.boundValueOps(TokenCacheKey.getUserTokenKey(HoleUtils.getUserId(request)))
                    .get();
            System.out.println(token);
            System.out.println(o);
            if(token.equals(o))
                return HoleResult.success();
        }
        return wxService.wxLogin(response,code,nickName);

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
