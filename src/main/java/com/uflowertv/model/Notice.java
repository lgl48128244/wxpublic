package com.uflowertv.model;

import org.apache.commons.lang3.StringUtils;

import com.uflowertv.util.GUIDUtil;

public class Notice {
    private String id;

    private String wxUserId;

    private String wxUserPhone;

    private String wxUserPicname;

    private String wxQuestionType;

    private String replyHuman;

    private String createtime;

    private String completetime;

    private Integer status;

    public String getId() {
    	if(StringUtils.isBlank(id)) return GUIDUtil.get();
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId == null ? null : wxUserId.trim();
    }

    public String getWxUserPhone() {
        return wxUserPhone;
    }

    public void setWxUserPhone(String wxUserPhone) {
        this.wxUserPhone = wxUserPhone == null ? null : wxUserPhone.trim();
    }

    public String getWxUserPicname() {
        return wxUserPicname;
    }

    public void setWxUserPicname(String wxUserPicname) {
        this.wxUserPicname = wxUserPicname == null ? null : wxUserPicname.trim();
    }

    public String getWxQuestionType() {
        return wxQuestionType;
    }

    public void setWxQuestionType(String wxQuestionType) {
        this.wxQuestionType = wxQuestionType == null ? null : wxQuestionType.trim();
    }

    public String getReplyHuman() {
        return replyHuman;
    }

    public void setReplyHuman(String replyHuman) {
        this.replyHuman = replyHuman == null ? null : replyHuman.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime == null ? null : completetime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}