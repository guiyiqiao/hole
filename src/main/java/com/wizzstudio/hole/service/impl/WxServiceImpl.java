package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.model.constant.TokenConstant;
import com.wizzstudio.hole.model.wx.Code2SessionResponse;
import com.wizzstudio.hole.service.WxService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.TokenUtil;
import com.wizzstudio.hole.util.WxChatUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadLocalRandom;
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

    private ThreadLocalRandom random = ThreadLocalRandom.current();
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
        User user = userMapper.queryUserByOpenId(openid);
        //地址分配算法,随机分配树洞名称
        Integer addr = 50 + random.nextInt() % 50;
        if(user == null){
            user = User.UserBuilder.anUser()
                .withOpenId(openid)
                .withNickName(StringUtils.isEmpty(nickName)?openid:nickName)
                .withAddress(addr)
                .build();
            int t = userMapper.insertUser(user);
            if(t <= 0)
                return HoleResult.failure("用户登陆认证失败，请重新登陆！");
            user = userMapper.queryUserByOpenId(openid);
        }
        //登陆认证成功，添加用户token
        String token = tokenUtil.sign(user.getId(),System.currentTimeMillis());
        redisTemplate.boundValueOps(TokenConstant.USER_TOKEN.toString()+user.getId())
                .set(token,tokenUtil.EXPIRE_TIME, TimeUnit.HOURS);
        //实现登陆逻辑，存token
        String refreshToken = token;
        redisTemplate.boundValueOps(TokenConstant.USER_REFRESH_TOKEN.toString()+user.getId())
                .set(token,tokenUtil.EXPIRE_TIME, TimeUnit.HOURS);
        httpResponse.setHeader("Authorization",token);

        return HoleResult.HoleResultBuilder.HoleResult()
                .withCode(200)
                .withMessage("登陆成功！")
                .withData(token)
                .build();
    }


}
