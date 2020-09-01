package com.wizzstudio.hole.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class TokenUtil {
    private static Logger log = LoggerFactory.getLogger(TokenUtil.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Value("${hole.jwt.expire.token:300}")
    public  long EXPIRE_TIME ;//token到期时间默认5分钟，秒为单位

    @Value("${hole.jwt.expire.refresh:1800}")
    public  long REFRESH_EXPIRE_TIME;;//RefreshToken到期时间为30分钟，秒为单位

    @Value("${hole.jwt.secret:ljdyaishijin**3nkjnj??}")
    private  String TOKEN_SECRET;  //密钥盐

    public String sign(Integer userId, Long currentTime){

        Date expireTime=new Date(currentTime+EXPIRE_TIME);
        String token = JWT.create()
                .withClaim("userId",userId)
                .withClaim("currentTime",currentTime)
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        return token;
    }


    public Boolean verify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();//创建token验证器
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Date expiresAt = decodedJWT.getExpiresAt();
        if(expiresAt.before(new Date())){
            //token过期了
            return false;
        }
        log.info("token认证通过."+"Id: " + decodedJWT.getClaim("userId").asString()+"过期时间：      " + expiresAt);
        return true;
    }



    public static Integer getUserId(String token){
        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("userId").asInt();
    }
    public Long getCurrentTime(String token){

        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("currentTime").asLong();

    }

}