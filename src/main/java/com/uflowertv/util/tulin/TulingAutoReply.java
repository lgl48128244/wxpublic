package com.uflowertv.util.tulin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.uflowertv.commons.BaseService;
import com.uflowertv.util.HttpClientUtils;
import com.uflowertv.util.JsonUtils;

/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：调用图灵机器人api接口，获取智能回复内容
 * 类名称：com.uflowertv.util.tulin.TulingAutoReply     
 * 创建人：liguoliang 
 * 创建时间：2016年8月22日 下午3:36:16   
 * 修改人：
 * 修改时间：2016年8月22日 下午3:36:16   
 * 修改备注：   
 * @version   V1.0
 */
public class TulingAutoReply extends BaseService{
	
	
	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * @Title: getTulingResult
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param content
	 * @return
	 */
	public static String getTulingResult(String content){
		/** 此处为图灵api接口，参数key需要自己去注册申请*/
		String apiUrl = "http://www.tuling123.com/openapi/api?key=34bee4e38b1d406c98ec1f0077eaaaab&info=";
		String param = "";
		String result = "";
		try {
			param = apiUrl+URLEncoder.encode(content,"utf-8");
			result = HttpClientUtils.get(param);
			int code = (int) JsonUtils.json2Map(result).get("code");
			//以code=100000为例，参考图灵机器人api文档
			if(100000==code){
				result = (String) JsonUtils.json2Map(result).get("text");
				return result;
			}else{
				return "不知所云";
			}
		} catch (UnsupportedEncodingException e) {
			log.error("编码格式错误:"+e.getMessage());
			return null;
		}
	}
}
