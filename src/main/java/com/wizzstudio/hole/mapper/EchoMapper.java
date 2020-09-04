package com.wizzstudio.hole.mapper;

import com.wizzstudio.hole.model.Echo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:26
 * @Version 1.0
 */
@org.apache.ibatis.annotations.Mapper
public interface EchoMapper extends Mapper<Echo> {

    List<Echo> timeline(Integer userId);

    int openEcho(Integer echoId,Integer userId);

    int deleteEcho(Integer blogId,Integer userId);

    int updateBatch(@Param("map") Map<Integer,Integer> map);
}
