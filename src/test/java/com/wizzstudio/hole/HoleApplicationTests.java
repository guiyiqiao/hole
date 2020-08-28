package com.wizzstudio.hole;

import com.wizzstudio.hole.mapper.UserMapper;
import com.wizzstudio.hole.model.User;
import com.wizzstudio.hole.service.WxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@SpringBootTest
class HoleApplicationTests {

    @Autowired
    private WxService wxService;

    @Resource
    private UserMapper userMapper;
    @Test
    void contextLoads() {

        //wxService.wxLogin();
    }

}
