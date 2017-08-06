package com.uflowertv.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;

import com.uflowertv.bean.ResponseError;
import com.uflowertv.menu.model.Button;
import com.uflowertv.menu.model.ClickButton;
import com.uflowertv.menu.model.Menu;
import com.uflowertv.menu.model.ViewButton;
import com.uflowertv.model.AccessToken;
import com.uflowertv.model.TransParmeter;
import com.uflowertv.model.TransResult;
import com.uflowertv.util.ConstantHolder;
import com.uflowertv.util.HttpClientUtils;
import com.uflowertv.util.JsonUtils;


/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：微信工具类
 * 类名称：com.imooc.util.WeixinUtil     
 * 创建人：liguoliang 
 * 创建时间：2016年8月9日 下午5:19:54   
 * 修改人：
 * 修改时间：2016年8月9日 下午5:19:54   
 * 修改备注：   
 * @version   V1.0
 */
public class BaseFunction {
	
	private static Log log = LogFactory.getLog(BaseFunction.class);
	
	private static AccessToken accessToken;
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() {
		if (accessToken != null) {
			/**
			 * 访问秘钥有效时间是2小时，大于2小时重新生成
			 * */
			String millis = accessToken.getExpiresIn();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.valueOf(millis));
			calendar.add(Calendar.HOUR_OF_DAY, 2);
			Date date = new Date();
			if (date.getTime() > calendar.getTimeInMillis()) {
				accessToken = getNewAcessToken();
			}
		} else {
			accessToken = new AccessToken();
			accessToken = getNewAcessToken();
		}
		return accessToken;
	}

	private static AccessToken getNewAcessToken() {
		// 令牌接口地址
		String url = ConstantHolder.ACCESS_TOKEN_URL.replace("APPID", ConstantHolder.APPID).replace("APPSECRET", ConstantHolder.APPSECRET);
		String json =  HttpClientUtils.get(url);
		accessToken = new AccessToken();
		if(json!=null){
			accessToken.setToken((String)JsonUtils.json2Map(json).get("access_token"));
			accessToken.setExpiresIn(String.valueOf(new Date().getTime()));
		}
		log.info("token:"+accessToken.getToken());
		return accessToken;
	}
	
	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu(){
		
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://uflowertv.ngrok.cc/Weixin/html/user_question.html");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button11,button,button21});
		return menu;
	}
	
	public static String createMenu(String token,String message){
		String url = ConstantHolder.CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		String json = HttpClientUtils.jaxRestful(url, message);
		ResponseError response = JsonUtils.json2Bean(json, ResponseError.class);
		if(response.getErrcode() !=0){
			log.error("创建菜单失败," + " 返回Code: " + response.getErrcode() + "返回Message: " + response.getErrmsg());
			return response.getErrmsg();
		}
		return response.getErrmsg();
	}
	/**
	 * 查询菜单
	 * @Title: queryMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param token
	 * @return
	 */
	public static Menu queryMenu(String token){
		String url = ConstantHolder.QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		String json = HttpClientUtils.get(url);
		//{"errcode":46003,"errmsg":"menu no exist hint: [8OTg1a0836vr18]"}
		Menu menu = JsonUtils.json2Bean(json, Menu.class);
		if(menu.getErrcode() != 0){
			log.error("查询菜单失败," + " 返回Code: " + menu.getErrcode() + "返回Message: " + menu.getErrmsg());
			return null;
		}
		return menu;
	}
	
	/**
	 * 删除菜单
	 * @Title: deleteMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param token
	 * @return
	 */
	public static String deleteMenu(String token) {
		String url = ConstantHolder.DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		String json = HttpClientUtils.get(url);
		ResponseError rep = JsonUtils.json2Bean(json, ResponseError.class);
		if(rep.getErrcode() != 0){
			log.error("删除菜单失败," + " 返回Code: " + rep.getErrcode() + "返回Message: " + rep.getErrmsg());
			return rep.getErrmsg();
		}
		return rep.getErrmsg();
	}
	
	
	/**
	 * 百度翻译
	 * @Title: baidufanyi
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param words
	 * @return
	 */
	public static String baidufanyi(String words){
		// 对appId+源文+随机数+token计算md5值
		String appid = ConstantHolder.FANYI_APPID;
		String salt = ConstantHolder.FANYI_SALT;
		String sign = DigestUtils.md5Hex(new StringBuilder().append(appid).append(words).append(salt).append(ConstantHolder.FANYI_TOKEN).toString());
		TransParmeter params = new TransParmeter(words,ConstantHolder.FANYI_FROM,ConstantHolder.FANYI_TO,appid,salt,sign);
		String json = HttpClientUtils.post(ConstantHolder.BAIDUFANYI, params);
		//{"from":"zh","to":"en","trans_result":[{"src":"\u7231\u4f60","dst":"love you"}]}
		TransParmeter transParmeter = JsonUtils.json2Bean(json, TransParmeter.class);
		String result = null;
		if(transParmeter.getError_code()!=0){
			log.error("翻译失败," + " 返回Code: " + transParmeter.getError_code() + "返回Message: " + transParmeter.getError_msg());
			return transParmeter.getError_msg();
		}
		List<TransResult> list = JsonUtils.json2List(transParmeter.getTrans_result(), TransResult.class);
		StringBuffer dst = new StringBuffer();
		for (TransResult transResult : list) {
			dst.append(transResult.getDst());
		}
		try {
			result = URLDecoder.decode(dst.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.getStackTrace();
		}
		return result;
	}
}
