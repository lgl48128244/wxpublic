package com.uflowertv.model;

public class NoticeWithBLOBs extends Notice {
    private String wxUserQuestion;

    private String replyQuestion;

    public String getWxUserQuestion() {
        return wxUserQuestion;
    }

    public void setWxUserQuestion(String wxUserQuestion) {
        this.wxUserQuestion = wxUserQuestion == null ? null : wxUserQuestion.trim();
    }

    public String getReplyQuestion() {
        return replyQuestion;
    }

    public void setReplyQuestion(String replyQuestion) {
        this.replyQuestion = replyQuestion == null ? null : replyQuestion.trim();
    }
}