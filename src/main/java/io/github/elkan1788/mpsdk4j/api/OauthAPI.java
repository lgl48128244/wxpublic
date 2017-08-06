package io.github.elkan1788.mpsdk4j.api;

/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：io.github.elkan1788.mpsdk4j.api.OauthAPI     
 * 创建人：liguoliang 
 * 创建时间：2016年8月22日 下午12:36:17   
 * 修改人：
 * 修改时间：2016年8月22日 下午12:36:17   
 * 修改备注：   
 * @version   V1.0
 */
public interface OauthAPI {
	/**
	 * 微信网页授权
	 */
	public static final String OAUTH_ADDR = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=xyz#wechat_redirect";
    /**
     * 网页授权获取TOKEN
     */
    public static final String OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    /**
     * 网页授权取得用户信息地址
     */
    public static final String OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s";
    
}
