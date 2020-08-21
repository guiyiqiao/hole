package com.wizzstudio.hole.util;
;
import com.alibaba.fastjson.JSON;
import com.wizzstudio.hole.model.wx.Code2SessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



/**
 * @Author 桂乙侨
 * @Date 2020/7/19 11:44
 * @Version 1.0
 */
@Component
public class WxChatUtil {

    private static Logger log = LoggerFactory.getLogger(WxChatUtil.class);


    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grant_type;

    @Autowired
    private RestTemplate restTemplate;
    

    public Code2SessionResponse doAuth(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                +appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if(!entity.getStatusCode().is2xxSuccessful()){
            log.error("微信认证时，出现错误！");
            return null;
        }
        Code2SessionResponse response = JSON.parseObject(entity.getBody(),Code2SessionResponse.class);
        return response;
    }
}
