package com.wizzstudio.hole.controller;

import com.wizzstudio.hole.annotation.UserLogin;
import com.wizzstudio.hole.model.BlogReport;
import com.wizzstudio.hole.model.CommentReport;
import com.wizzstudio.hole.service.ReportService;
import com.wizzstudio.hole.util.HoleResult;
import com.wizzstudio.hole.util.UserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 举报相关controller接口
 * @Author 桂乙侨
 * @Date 2020/8/27 19:19
 * @Version 1.0
 */
@RestController
@RequestMapping("hole/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 需求2.对心事的举报操作
     * report 记为举报
     * @param blogId  被举报的心事id
     * @param reason 举报原因
     * @return
     */
    @PostMapping("blog")
    @UserLogin
    public HoleResult reportBlog(@RequestParam("blogId") Integer blogId,
                             @RequestParam("reason") String reason,
                             HttpServletRequest request){
        BlogReport blogReport = BlogReport.BlogReportBuilder.aBlogReport()
                .withUserId(UserIdUtil.getUserId(request))
                .withBlogId(blogId)
                .withReason(reason)
                .build();
        return  reportService.report(blogReport);
    }


    /**
     * 需求三 举报回声
     * @param commentId
     * @param reason
     * @param request
     * @return
     */
    @PostMapping("comment")
    @UserLogin
    public HoleResult reportComment(@RequestParam("commentId") Integer commentId,
                             @RequestParam("reason") String reason,
                             HttpServletRequest request){
        if(commentId<= 0 || StringUtils.isEmpty(reason))
            return HoleResult.failure("参数错误，请重试");
        CommentReport commentReport = CommentReport.CommentReportBuilder.aCommentReport()
                .withSolved(false)
                .withCommentId(commentId)
                .withReason(reason)
                .withUserId(UserIdUtil.getUserId(request))
                .build();
        return reportService.report(commentReport);
    }
}
