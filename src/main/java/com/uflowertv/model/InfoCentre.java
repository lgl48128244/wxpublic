package com.uflowertv.model;

import org.apache.commons.lang3.StringUtils;

import com.uflowertv.util.GUIDUtil;

public class InfoCentre {
    private String id;

    private String wxUserId;

    private String wxUserPhone;

    private String wxUserPicurl;

    private String wxUserMsgid;

    private String wxUserMediaid;

    private String wxPublicId;

    private String createtime;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String wxUserContent;

    public String getId() {
    	if(StringUtils.isBlank(id))return GUIDUtil.get();
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

    public String getWxUserPicurl() {
        return wxUserPicurl;
    }

    public void setWxUserPicurl(String wxUserPicurl) {
        this.wxUserPicurl = wxUserPicurl == null ? null : wxUserPicurl.trim();
    }

    public String getWxUserMsgid() {
        return wxUserMsgid;
    }

    public void setWxUserMsgid(String wxUserMsgid) {
        this.wxUserMsgid = wxUserMsgid == null ? null : wxUserMsgid.trim();
    }

    public String getWxUserMediaid() {
        return wxUserMediaid;
    }

    public void setWxUserMediaid(String wxUserMediaid) {
        this.wxUserMediaid = wxUserMediaid == null ? null : wxUserMediaid.trim();
    }

    public String getWxPublicId() {
        return wxPublicId;
    }

    public void setWxPublicId(String wxPublicId) {
        this.wxPublicId = wxPublicId == null ? null : wxPublicId.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getWxUserContent() {
        return wxUserContent;
    }

    public void setWxUserContent(String wxUserContent) {
        this.wxUserContent = wxUserContent == null ? null : wxUserContent.trim();
    }
}