package com.uflowertv.bean;

import org.apache.commons.lang3.StringUtils;

public class OauthAccessToken {

	private String access_token;
	private String expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	private String lang;
	
	
	
	public String getLang() {
		if(StringUtils.isBlank(lang))return "zh_CN";
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public OauthAccessToken() {
		// TODO Auto-generated constructor stub
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "OauthAccessToken [access_token=" + access_token
				+ ", expires_in=" + expires_in + ", refresh_token="
				+ refresh_token + ", openid=" + openid + ", scope=" + scope
				+ ", lang=" + lang + "]";
	}
}
