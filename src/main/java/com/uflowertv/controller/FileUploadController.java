package com.uflowertv.controller;


import io.github.elkan1788.mpsdk4j.api.WechatAPI;
import io.github.elkan1788.mpsdk4j.api.WechatAPIImpl;
import io.github.elkan1788.mpsdk4j.core.WechatDefaultHandler;
import io.github.elkan1788.mpsdk4j.mvc.WechatWebSupport;
import io.github.elkan1788.mpsdk4j.vo.MPAccount;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.uflowertv.util.ConstantHolder;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：文件上传
 * 类名称：com.uflowertv.notice.FileUploadController     
 * 创建人：liguoliang 
 * 创建时间：2016年8月19日 下午4:15:47   
 * 修改人：
 * 修改时间：2016年8月19日 下午4:15:47   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class FileUploadController extends WechatWebSupport{
	
	 private WechatAPI wechatAPI;
	 
	{
		MPAccount mpAct = new MPAccount();
        mpAct.setMpId(ConstantHolder.MPID);
        mpAct.setAppId(ConstantHolder.APPID);
        mpAct.setAppSecret(ConstantHolder.APPSECRET);
        mpAct.setToken(ConstantHolder.TOKEN);
        _wk.setMpAct(mpAct);
        _wk.setWechatHandler(new WechatDefaultHandler());
        // 实例化所有接口信息
        wechatAPI = WechatAPIImpl.create(mpAct);
	}
	
	/**
	 * 微信端上传图片
	 * @param pic
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/upload.do")
	@ResponseBody
	public Map<String,Object> upload(HttpServletRequest request,String mediaId) throws IOException {
		//绝对路径，用于保存图片
		String absolutePath = request.getSession().getServletContext().getRealPath("/user_question/");
		File path = new File(absolutePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		//此对象可以发送图片
		File file = wechatAPI.dlMedia(mediaId,absolutePath);
		//相对路径，用于显示图片
		String relativePath = request.getContextPath()+File.separator+"user_question"+File.separator;
		String fileName = file.getName();
		String url = relativePath + fileName;
		File targetFile = new File(absolutePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("path", fileName);
		map.put("url", url);
		return map;
		//ImageMsg imMsg = new ImageMsg();
		//wechatAPI.get
	}
	
	
	/**
	 * fckeditor编辑器上传图片
	 * @Title: uploadFck
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @throws IllegalStateException 
	 * @throws IOException
	 */
	@RequestMapping("/uploadFck.do")
	public void uploadFck(HttpServletRequest request) throws IllegalStateException, IOException{
		//介绍一款Springmvc的无敌接收方式  //Springmvc配置上传 
		//强转Request的
		MultipartRequest mr = (MultipartRequest)request;
		//接口  获取文件  支持多个  本次只一个
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile pic = entry.getValue();
			//年月日分时秒 （毫秒） + 随机数（三位）
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//图片名
			String name = df.format(new Date());
			Random r = new Random();
			for (int i = 0; i < 3; i++) {
				name += r.nextInt(10);
			}
			//一款可以获取扩展名
			String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
			String fileName = name + "." + ext; //png
			//给请求路径 URL
			//绝对路径，用于保存图片
			String absolutePath = request.getSession().getServletContext().getRealPath("/fckeditor/");
			File targetFile = new File(absolutePath, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			//file.getFileItem().write(targetFile); //将上传的文件写入新建的文件中
			pic.transferTo(targetFile);
		}
	}
	/**
	 * 上传图片
	 * @param pic
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/uploadPic.do")
	@ResponseBody
	public Map<String,String> uploadPic(@RequestParam CommonsMultipartFile pic,HttpServletRequest request) throws IllegalStateException, IOException{
		//获取文件真实名称
		//String name = pic.getOriginalFilename();
		//System.out.println(filename);
		//年月日分时秒 （毫秒） + 随机数（三位）
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//图片名
		String name = df.format(new Date());
		
		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			name += r.nextInt(10);
		}
		//一款可以获取扩展名
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		
		//1 返回相对路径
		String fileName = name + "." + ext; //png
		//给请求路径 URL
		//2
		//绝对路径，用于保存图片
		String absolutePath = request.getSession().getServletContext().getRealPath("/download/");
		//相对路径，用于显示图片
		String relativePath = request.getContextPath()+File.separator+"download"+File.separator;
		String url = relativePath + fileName;
		File targetFile = new File(absolutePath, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		//file.getFileItem().write(targetFile); //将上传的文件写入新建的文件中
		pic.transferTo(targetFile);
		//返回  path url 
		Map<String,String> map = new HashMap<String,String>();
		map.put("path", fileName);
		map.put("url", url);
		return map;
	}
	
}
