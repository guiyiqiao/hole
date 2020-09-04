package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.Echo;
import com.wizzstudio.hole.util.HoleResult;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:36
 * @Version 1.0
 */
public interface EchoService {

    HoleResult addEcho(Echo echo);

    HoleResult thank(Integer echoId);

    int getThank(Integer echoId);

    HoleResult openEcho(Integer echoId,Integer userId);

    HoleResult deleteEcho(Integer echoId,Integer userId);

    HoleResult listOvertEcho(int pageNum,int pageSize);
}
