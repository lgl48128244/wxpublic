package com.uflowertv.controller.front;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.uflowertv.commons.LogUtil;
import com.uflowertv.model.PublicEntity;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：wxpublic   
 *
 * 类描述：前端接口调用业务功能
 * 类名称：com.uflowertv.controller.front.FrontControllerService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月30日 下午6:25:15   
 * 修改人：
 * 修改时间：2016年8月30日 下午6:25:15   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
@RequestMapping("/front")
public class FrontControllerService {
		
	private static final String ENCODING = "UTF-8";

   /**  
	 * 
	 * @Title: logon
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/xxxx.shtml")
	public void xxxx(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String mode = null;
		String resp = null;
		Gson gson = new Gson();
		String req = errorException(request, response, gson, resp);
		PublicEntity entity = gson.fromJson(req, PublicEntity.class);
		mode = entity.getMode();
		try {
			//调用对应接口
			if ("1.0".equals(mode)) {
				//resp = frontService.getHomePageInfo(req);
			}
			// 发送json对象
			sendJsonObject(response, resp);
		} catch (Exception e) {
			LogUtil.printLog("error", e.getMessage());
			errorException(request, response, gson, req);
		}
	}
		
  
	private String errorException(HttpServletRequest request, HttpServletResponse response, Gson gson, String resp) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), ENCODING));
		PublicEntity entity = new PublicEntity();
		String req = br.readLine();
		if (StringUtils.isBlank(req)) {
			entity.setErrorcode("-1");
			entity.setErrorinfo("程序发生异常。");
			resp = gson.toJson(entity);
			sendJsonObject(response, resp);
		}
		return req;
	}

	private void sendJsonObject(HttpServletResponse response, String json) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding(ENCODING);
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		out.close();
	}
}
