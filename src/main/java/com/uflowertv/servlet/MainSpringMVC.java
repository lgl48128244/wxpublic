package com.uflowertv.servlet;

import io.github.elkan1788.mpsdk4j.api.WechatAPI;
import io.github.elkan1788.mpsdk4j.api.WechatAPIImpl;
import io.github.elkan1788.mpsdk4j.core.WechatDefaultHandler;
import io.github.elkan1788.mpsdk4j.mvc.WechatWebSupport;
import io.github.elkan1788.mpsdk4j.util.ConfigReader;
import io.github.elkan1788.mpsdk4j.vo.MPAccount;
import io.github.elkan1788.mpsdk4j.vo.api.Menu;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uflowertv.base.WxWebOauth;
import com.uflowertv.jssdk.JSTicketUtil;
import com.uflowertv.model.Follower;
import com.uflowertv.service.FollowerService;
import com.uflowertv.service.MenuService;
import com.uflowertv.util.ConstantHolder;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：微信入口
 * 类名称：com.uflowertv.servlet.MainSpringMVC     
 * 创建人：liguoliang 
 * 创建时间：2016年8月22日 下午3:10:26   
 * 修改人：
 * 修改时间：2016年8月22日 下午3:10:26   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
@RequestMapping("/main")
public class MainSpringMVC extends WechatWebSupport{
	private static final ConfigReader CONFIG_READER = new ConfigReader("/mpconf.properties");
	
	//声明接口
	private WechatAPI wechatAPI;
	@Autowired
	private FollowerService followerService;
	@Autowired
	private MenuService menuService;
	
	/*
	 *普通代码块加载参数 
	 */
	{
		MPAccount mpAct = new MPAccount();
        mpAct.setMpId(CONFIG_READER.get("mpId"));
        mpAct.setAppId(CONFIG_READER.get("appId"));
        mpAct.setAppSecret(CONFIG_READER.get("appSecret"));
        mpAct.setToken(CONFIG_READER.get("token"));
        _wk.setMpAct(mpAct);
        _wk.setWechatHandler(new WechatDefaultHandler());
        // 实例化所有接口信息
        wechatAPI = WechatAPIImpl.create(mpAct);
	}

	@RequestMapping("/wxservice.do")
    public void wechatEntry(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		this.interact(req, resp);
		String openId = _wk.get("openid");
		//更新或保存用户
		saveOrUpdateFollower(openId);
		//更新菜单
		//updateMenu();
	}
	
	/**
	 * 配置jssdk
	 * @Title: jssdk
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/jssdk.do")
	@ResponseBody
	public Map<String , String> jssdk(String url){
		String jsapi_ticket = wechatAPI.getJSTicket();
 		Map<String , String> map = JSTicketUtil.sign(jsapi_ticket, url);
 		map.put("appId", ConstantHolder.APPID);
 		return map;
	}
    
	
   /**
	 * 获取用户信息
	 * @Title: doGet
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/userinfo.do")
	public String doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		String param = request.getQueryString();
		String redirect_url = request.getRequestURL().toString()+"?"+param;
        if(StringUtils.isBlank(code)){
        	 response.sendRedirect(WxWebOauth.authAddress(redirect_url));
        	 return null;
        }else{
        	Follower follower = WxWebOauth.getUserInfo(ConstantHolder.APPID, ConstantHolder.APPSECRET, code);
        	request.setAttribute("follower", follower);
        	if("user_question".equals(type)){
        		//获取用户信息
        		return "/wxpage/user_question";
        	}
        	return null;
        }
	}
	
	/**
	 * 保存或更新用户信息
	 * @Title: saveOrUpdateFollower
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param follower
	 */
	private void saveOrUpdateFollower(String openid){
		
		if(StringUtils.isNotBlank(openid)){
			Follower follower = wechatAPI.getFollower(openid, null);
			Follower f = followerService.getFollower(openid);
			if(f == null){
				followerService.saveFollower(follower);
			}else{
				followerService.updateFollower(follower);
			}
		}
	}
	
	/**
	 * 更新菜单
	 * @Title: updateMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	public void updateMenu(){
		Menu[] menus = menuService.createMenu();
		wechatAPI.createMenu(menus);
	}
}
