package com.uflowertv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.uflowertv.bean.ResponseError;
import com.uflowertv.commons.StringDeserializer;

public class TransParmeter extends ResponseError{
	//请求翻译query	UTF-8编码
	private String q;
	
	//翻译源语言语言列表(可设置为auto)
	private String from;
	
	//语言列表(不可设置为auto)
	private String to;

	//可在管理控制台查看
	private String appid;
	
	//随机数	
	private String salt;
	
	//签名	appid+q+salt+密钥 的MD5值
	private String sign;
	
	private String monLang;
	private String query;
	@JsonDeserialize(using=StringDeserializer.class)
	private String trans_result;
	
	public TransParmeter() {
		
	}

	
	public TransParmeter(String q, String from, String to, String appid,
			String salt, String sign) {
		super();
		this.q = q;
		this.from = from;
		this.to = to;
		this.appid = appid;
		this.salt = salt;
		this.sign = sign;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getMonLang() {
		return monLang;
	}


	public void setMonLang(String monLang) {
		this.monLang = monLang;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getTrans_result() {
		return trans_result;
	}


	public void setTrans_result(String trans_result) {
		this.trans_result = trans_result;
	}
	
}
