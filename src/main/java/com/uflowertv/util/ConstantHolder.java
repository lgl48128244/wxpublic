package com.uflowertv.util;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.util.ConstantHolder     
 * 创建人：liguoliang 
 * 创建时间：2016年8月9日 下午7:50:09   
 * 修改人：
 * 修改时间：2016年8月9日 下午7:50:09   
 * 修改备注：   
 * @version   V1.0
 */
public class ConstantHolder {
	//SFTP参数
	public static final String SFTP_REQ_HOST = "123.56.244.170";
	public static final String SFTP_REQ_PORT = "22";
	public static final int SFTP_DEFAULT_PORT = 22;
	public static final String SFTP_REQ_USERNAME = "root";
	public static final String SFTP_REQ_PASSWORD = "19850212Matao";
	//短信参数
	public static final String APIKEY = "6cab0fe2c35609be13fb0dc8b8e47a6d";
	public static final String SECRETKEY = "C82EFFD221BBE2A6";
	public static final String TEMPLATEID = "1064";
	public static final String SMSURL = "http://api.sms.testin.cn/sms"; 
	public static final String SEND_MESSAGE = "1";
	//微信参数
	public static final String IMG_SERVER = "http://weixin.u-flower.net/img_server/imgfolder/";
	public static final String APPID = "wxa3aa3c6f86e4f0d8";
	public static final String MPID = "gh_faaed5596ce0";
	public static final String APPSECRET = "7e53a1078cfd00303900bfae6f610582";
	public static final String TOKEN = "uflowertv";
	//百度翻译参数
	public static final String FANYI_APPID = "2015063000000001"; 
	public static final String FANYI_FROM = "auto";
	public static final String FANYI_TO = "auto";
	public static final String FANYI_SALT ="1435660288";
	public static final String FANYI_TOKEN = "12345678";
	
	//微信接口
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//百度翻译接口
	public static final String BAIDUFANYI = "http://api.fanyi.baidu.com/api/trans/vip/translate";

	
	
	
}
