package com.uflowertv.controller.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uflowertv.util.ConstantHolder;
import com.uflowertv.util.JsonUtils;
import com.uflowertv.util.MessageUtil;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/mobile")
public class FrontLoginController {

	/**
	 * 
	 * @param mobilePhone
	 * @param authCode
	 * @return
	 */
	@RequestMapping("/modify_customer_info_phone.shtml")
	@ResponseBody
	public Map<String,Object> modify_customer_info_phone(String mobilePhone, String authCode){
		Map<String,Object> map = new HashMap<String,Object>();
		int count = 1;
		if(count > 0){
			map.put("resultCode", 0);
			return map;
		}else{
			map.put("errorCode", "注册失败");
			return map;
		}
	}
	/**
	 * 
	 * @param mobilePhone
	 * @param authCode
	 * @return
	 */
	@RequestMapping("/check_code.shtml")
	@ResponseBody
	public Map<String,Object> check_code(HttpSession session,String mobilePhone, String catpcha,String isRegisterValidate){
		Map<String,Object> map = new HashMap<String,Object>();
		String code = (String) session.getAttribute("code");
		if(catpcha.toUpperCase().equals(code)){
			 boolean sendMessage = isRegisterValidate.equals(ConstantHolder.SEND_MESSAGE);
			// 发送验证码------生产发，测试环境不发
	        if (sendMessage) {
	        	String validata = MessageUtil.madeVali();
				String paras = MessageUtil.getMsgJson(mobilePhone, validata);
				String result = MessageUtil.transmessage(ConstantHolder.SMSURL, paras);
				int resultCode = (int) JsonUtils.json2Map(result).get("code");
				String resultMsg = (String) JsonUtils.json2Map(result).get("msg");
				if(resultCode == 1000){
					/*save*/
				}else{
					map.put("errorCode", resultMsg);
					return map;
				}
			}
	        map.put("resultCode", 100000);
			return map;
		}else{
			map.put("errorCode", "图片验证码无效");
			return map;
		}
	}
}
