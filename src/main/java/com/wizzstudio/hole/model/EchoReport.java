package com.wizzstudio.hole.model;

import javax.persistence.Id;

/**
 * @Author 桂乙侨
 * @Date 2020/8/29 15:24
 * @Version 1.0
 */
public class EchoReport {
    @Id
    private Integer id;

    private Integer echoId;

    //举报原因
    private String reason;

    private Integer userId;

    //是否已经被处理
    private Boolean solved;

    @Override
    public String toString() {
        return "EchoReport{" +
                "id=" + id +
                ", echoId=" + echoId +
                ", reason='" + reason + '\'' +
                ", userId=" + userId +
                ", solved=" + solved +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEchoId() {
        return echoId;
    }

    public void setEchoId(Integer echoId) {
        this.echoId = echoId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public static final class EchoReportBuilder {
        private Integer id;
        private Integer echoId;
        //举报原因
        private String reason;
        private Integer userId;
        //是否已经被处理
        private Boolean solved;

        private EchoReportBuilder() {
        }

        public static EchoReportBuilder anEchoReport() {
            return new EchoReportBuilder();
        }

        public EchoReportBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public EchoReportBuilder withEchoId(Integer echoId) {
            this.echoId = echoId;
            return this;
        }

        public EchoReportBuilder withReason(String reason) {
            this.reason = reason;
            return this;
        }

        public EchoReportBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public EchoReportBuilder withSolved(Boolean solved) {
            this.solved = solved;
            return this;
        }

        public EchoReport build() {
            EchoReport echoReport = new EchoReport();
            echoReport.setId(id);
            echoReport.setEchoId(echoId);
            echoReport.setReason(reason);
            echoReport.setUserId(userId);
            echoReport.setSolved(solved);
            return echoReport;
        }
    }
}
