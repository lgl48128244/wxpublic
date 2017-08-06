package com.uflowertv.base;

import io.github.elkan1788.mpsdk4j.api.OauthAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.uflowertv.bean.OauthAccessToken;
import com.uflowertv.model.Follower;
import com.uflowertv.util.ConstantHolder;
import com.uflowertv.util.HttpClientUtils;
import com.uflowertv.util.JsonUtils;

/**
 * 微信网页授权
 *
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2014/06/11
 */
public class WxWebOauth {

    protected static final Log log = Logs.get();
	private static OauthAccessToken oauthAccessToken;
		
	/**
	 * 获取oauthAccessToken
	 * @Title: getAccessToken
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static OauthAccessToken getAccessToken(String appid,String secret,String code) {
		if (oauthAccessToken != null) {
			log.infof("获取oauthAccessToken:%s",oauthAccessToken.toString());
			/**
			 * 访问秘钥有效时间是2小时，大于2小时重新生成
			 * */
			String millis = oauthAccessToken.getExpires_in();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.valueOf(millis));
			calendar.add(Calendar.HOUR_OF_DAY, 2);
			Date date = new Date();
			if (date.getTime() > calendar.getTimeInMillis()) {
				oauthAccessToken = getNewAcessToken(appid,secret,code);
			}
		} else {
			oauthAccessToken = new OauthAccessToken();
			oauthAccessToken = getNewAcessToken(appid,secret,code);
		}
		return oauthAccessToken;
	}

	private static OauthAccessToken getNewAcessToken(String appid,String secret,String code) {
		// 令牌接口地址
		String oauthUrl = String.format(OauthAPI.OAUTH_TOKEN,appid,secret,code);		
		String json =  HttpClientUtils.get(oauthUrl);
		log.infof("授权回调信息: %s",json);
		oauthAccessToken = new OauthAccessToken();
		if(json!=null){
			oauthAccessToken.setAccess_token((String)JsonUtils.json2Map(json).get("access_token"));
			oauthAccessToken.setExpires_in(String.valueOf(new Date().getTime()));
		}
		return oauthAccessToken;
	}
    
    /**
     * 网页授权取得用户信息
     * @Title: getUserInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static Follower getUserInfo(String appid,String secret,String code) throws UnsupportedEncodingException{
    	 OauthAccessToken webOauth = getAccessToken(appid,secret,code);
		 String url = String.format(OauthAPI.OAUTH_USERINFO, webOauth.getAccess_token(),webOauth.getOpenid(),webOauth.getLang());
		 String result = HttpClientUtils.get(url);
		 Follower follower = JsonUtils.json2Bean(result, Follower.class);
		 log.infof("网页授权取得用户信息:%s",follower.toString());
		 return follower;
    }
    
	/**
	 * 微信网页授权请求地址
	 * @Title: authAddress
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	public static String authAddress(String redirect_url){  
		log.info("发送网页授权请求地址");
	    try {
			String url = String.format(OauthAPI.OAUTH_ADDR,ConstantHolder.APPID,URLEncoder.encode(redirect_url, "UTF-8"));
			return url;
		} catch (UnsupportedEncodingException e) {
			log.infof("编码格式错误：%s", e.getMessage());
			return null;
		}
	 } 
}
