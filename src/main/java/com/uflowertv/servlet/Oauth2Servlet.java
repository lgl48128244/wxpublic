package com.uflowertv.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.uflowertv.base.WxWebOauth;
import com.uflowertv.model.Follower;
import com.uflowertv.util.ConstantHolder;

/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：微信网页授权
 * 类名称：com.uflowertv.servlet.Oauth2Servlet     
 * 创建人：liguoliang 
 * 创建时间：2016年8月22日 上午10:38:52   
 * 修改人：
 * 修改时间：2016年8月22日 上午10:38:52   
 * 修改备注：   
 * @version   V1.0
 */
@WebServlet("/Oauth2Servlet")
public class Oauth2Servlet extends HttpServlet {
    private static final long serialVersionUID = -644518508267758016L;

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    	doGet(req, resp);
	}
    
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		String redirect_url = request.getRequestURL().toString();
        if(StringUtils.isBlank(code)){
        	 response.sendRedirect(WxWebOauth.authAddress(redirect_url));
        	 return;
        }else{
        	//获取用户信息
        	Follower follower = WxWebOauth.getUserInfo(ConstantHolder.APPID, ConstantHolder.APPSECRET, code);
        	request.setAttribute("follower", follower);
        	request.getRequestDispatcher("/wxpage/user_question.jsp").forward(request, response);
        	return;
          }
    }
}
