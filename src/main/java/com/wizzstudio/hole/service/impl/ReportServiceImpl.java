package com.wizzstudio.hole.service.impl;

import com.wizzstudio.hole.mapper.BlogReportMapper;
import com.wizzstudio.hole.mapper.CommentReportMapper;
import com.wizzstudio.hole.mapper.EchoReportMapper;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.model.EchoReport;
import com.wizzstudio.hole.service.ReportService;
import com.wizzstudio.hole.util.HoleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 桂乙侨
 * @Date 2020/8/27 19:21
 * @Version 1.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private CommentReportMapper commentReportMapper;

    @Resource
    private BlogReportMapper blogReportMapper;

    @Resource
    private EchoReportMapper echoReportMapper;
    /**
     * 需求二、举报心事
     * 待完成
     * @return
     */
    @Override
    public HoleResult report(BlogReport report){
        int ret = blogReportMapper.insertSelective(report);
        return ret > 0 ? HoleResult.success():HoleResult.failure("拥抱失败请重试！");
    }

    /**
     * 需求三 举报回声
     * @param commentReport
     * @return
     */
    @Override
    public HoleResult report(CommentReport commentReport){
        int ret = commentReportMapper.insertSelective(commentReport);
        return ret > 0? HoleResult.success():HoleResult.failure();
    }

    @Override
    public HoleResult report(EchoReport echoReport) {
        int ret = echoReportMapper.insertSelective(echoReport);
        return ret > 0? HoleResult.success():HoleResult.failure();

    }
}
