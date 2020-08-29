package com.wizzstudio.hole.service;

import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.model.EchoReport;
import com.wizzstudio.hole.util.HoleResult;

/**
 * @Author 桂乙侨
 * @Date 2020/8/27 19:21
 * @Version 1.0
 */
public interface ReportService {

    HoleResult report(BlogReport report);

    HoleResult report(CommentReport commentReport);

    HoleResult report(EchoReport echoReport);
}
