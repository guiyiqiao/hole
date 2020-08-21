package com.wizzstudio.hole.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.wizzstudio.hole.annotation.PassToken;
import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.exception.AuthException;
import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.model.constant.TokenConstant;
import com.wizzstudio.hole.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author 桂乙侨
 * @Date 2020/4/2 10:13
 * @Version 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private TokenUtil tokenUtil;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("HandlerInterceptor  登陆认证拦截器 开始拦截");

        //不是映射到方法 直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLogin.class)) {
            String token = request.getHeader("Authorization");
            UserLogin userLogin = method.getAnnotation(UserLogin.class);
            if (userLogin.required()) {
                // 执行认证
                if (StringUtils.isEmpty(token)) {
                    throw new AuthException("无token，请先登录");
                }
                // 获取 token 中的 userid
                Integer userId;
                try {
                    userId = tokenUtil.getUserId(token);
                } catch (JWTDecodeException j) {
                    throw new AuthException("登陆信息异常，请登陆后重试！");
                }

                //查询用户是否存在
                User user = userMapper.queryUserById(userId);
                if (user == null|| !tokenUtil.verify(token)) {
                    throw new AuthException("用户信息错误，请登录!");
                }

                String access_token = (String) redisTemplate.boundValueOps(TokenConstant.USER_TOKEN.toString()+userId).get();

                if(access_token== null){
                    //access_token 过期
                    refreshToken(request,response);
                }else{
                    return token.equals(access_token);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("HandlerInterceptor 拦截完成");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     * @param request
     * @param response
     * @return
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {

        String token = ((HttpServletRequest)request).getHeader("Authorization");
        Integer userId = tokenUtil.getUserId(token);


        // 判断Redis中RefreshToken是否存在
        String refreshToken = (String) redisTemplate
                .boundValueOps(TokenConstant.USER_REFRESH_TOKEN.toString()+userId)
                .get();

        // Redis中RefreshToken还存在，获取RefreshToken的时间戳
        //Long timestamp = (Long) cache.get(userName);
        // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
        if (!StringUtils.isEmpty(refreshToken)) {
            // 获取当前最新时间戳
            Long currentTimeMillis = System.currentTimeMillis();
            //TokenUtil.REFRESH_EXPIRE_TIME);
            // 刷新AccessToken，设置时间戳为当前最新时间戳
            token = tokenUtil.sign(userId, currentTimeMillis);
            redisTemplate.boundValueOps(TokenConstant.USER_TOKEN.toString()+userId)
                    .set(token,tokenUtil.EXPIRE_TIME, TimeUnit.SECONDS);
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return true;
        }

        return false;
    }
}
