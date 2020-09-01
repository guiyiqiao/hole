package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.model.constant.TokenCacheKey;
import com.wizzstudio.hole.model.wx.Code2SessionResponse;
import com.wizzstudio.hole.service.WxService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import com.wizzstudio.hole.util.WxChatUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/7/19 11:42
 * @Version 1.0
 */
@Service
public class WxServiceImpl implements WxService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private WxChatUtil wxChatUtil;

    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 微信小程序登陆授权方法
     * 若用户未注册，则先注册
     * @param code wx登陆认证码
     * @param nickName 昵称，可为空
     * @return
     */

    @Override
    public HoleResult wxLogin(HttpServletResponse httpResponse, String code, String nickName) {

        if (StringUtils.isEmpty(code)) {
            return HoleResult.failure("微信认证码 code不能为空");
        }
        Code2SessionResponse response = wxChatUtil.doAuth(code);
        if(response == null || response.getErrcode() != null ){

            return HoleResult.failure("微信认证失败！");
        }
        //若未注册，则先注册
        String openid = response.getOpenid();

        //响应太慢了
        User queryUser = new User();
        queryUser.setOpenId(openid);
        User user = userMapper.selectOne(queryUser);
        if(user == null){
            user = User.UserBuilder.anUser()
                .withOpenId(openid)
                .withNickname(StringUtils.isEmpty(nickName)?openid:nickName)
                .build();
            int t = userMapper.insertUser(user);
            if(t <= 0)
                return HoleResult.failure("用户登陆认证失败，请重新登陆！");
            user = userMapper.selectOne(queryUser);
        }

        //登陆认证成功，添加用户token
        String token = (String) redisTemplate.boundValueOps(TokenCacheKey.getUserTokenKey(user.getId())).get();
        if(token == null){
            token = tokenUtil.sign(user.getId(),System.currentTimeMillis());
            redisTemplate.boundValueOps(TokenCacheKey.getUserTokenKey(user.getId()))
                    .set(token,tokenUtil.EXPIRE_TIME, TimeUnit.HOURS);
            //实现登陆逻辑，存token
            String refreshToken = token;
            redisTemplate.boundValueOps(TokenCacheKey.getUserRefreshTokenKey(user.getId()))
                    .set(token,tokenUtil.EXPIRE_TIME, TimeUnit.HOURS);
        }
        httpResponse.setHeader("Authorization",token);

        return HoleResult.HoleResultBuilder.HoleResult()
                .withCode(200)
                .withMessage("登陆成功！")
                .withData(token)
                .build();
    }


}
