package com.uflowertv.controller;

import io.github.elkan1788.mpsdk4j.core.XmlMsgBuilder;
import io.github.elkan1788.mpsdk4j.vo.message.TextMsg;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uflowertv.model.NoticeWithBLOBs;
import com.uflowertv.model.User;
import com.uflowertv.service.NoticeService;
import com.uflowertv.util.ConstantHolder;
import com.uflowertv.util.FreeMarkerTemplateUtil;
/**
 * 问题反馈
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.notice.controller.NoticeController     
 * 创建人：liguoliang 
 * 创建时间：2016年8月22日 下午3:09:08   
 * 修改人：
 * 修改时间：2016年8月22日 下午3:09:08   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
@RequestMapping("/notice")
public class NoticeController{
    @Autowired
    private NoticeService noticeService;
    
    /**
     * 回复内容
     * @param content
     * @param openid
     * @param response
     * @throws IOException
     */
    private void replyMsg(String content,String openid,HttpServletResponse response){
    	TextMsg msg = new TextMsg();
    	msg.setFromUserName(ConstantHolder.MPID);
    	msg.setToUserName(openid);
		msg.setContent(content);
		  // 输出回复消息
		String respmsg = XmlMsgBuilder.create().text(msg).build();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		try {
			response.getWriter().print(respmsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * 提交反馈问题
	 * @Title: submitQuestion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param noticeWithBLOBs
	 */
	@RequestMapping("/saveNotice.do")
	@ResponseBody
	public Map<String,Object> save(NoticeWithBLOBs noticeWithBLOBs,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		noticeWithBLOBs.setCreatetime(sdf.format(new Date()));
		int count = noticeService.saveNotice(noticeWithBLOBs);
		if(count > 0 ){
			map.put("message", "提交成功！");
			replyMsg("你的问题已经反馈，请耐心等待……", noticeWithBLOBs.getWxUserId(), response);
			return map;
		}else{
			map.put("message", "提交失败！");
			return map;
		}
	}
	
	/**
	 * 消息列表
	 * @Title: getNoticeNotHandler
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param rows
	 * @param type
	 * @return
	 */
	@RequestMapping("/getNoticeHandler.do")
	@ResponseBody
	public Map<String,Object> getNoticeNotHandler(int page,int rows,String type,NoticeWithBLOBs noticeWithBLOBs){
		return noticeService.list(page, rows, Integer.valueOf(type),noticeWithBLOBs);
	}
	
	/**
	 * 获取回复人昵称
	 * @Title: findByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findByUserId.do")
	@ResponseBody
	public User findByUserId(String userId){
		return noticeService.findByUserId(userId);
	}
	
	/**
	 * 查看详情
	 * @Title: toNoticeInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	@RequestMapping("/toNoticeInfo.do")
	public ModelAndView toNoticeInfo(String id,String status){
		ModelAndView mv = new ModelAndView("exception");
		NoticeWithBLOBs noticeWithBLOBs = noticeService.findById(id);
		if(noticeWithBLOBs!=null){
			mv.addObject("notice", noticeWithBLOBs);
			if("0".equals(status)){
				mv.setViewName("/notice/noticeInfo");
			}else if("3".equals(status)){
				mv.setViewName("/notice/noticeInfo");
			}else if("4".equals(status)){
				mv.setViewName("/notice/noticeInfo2");
			}else{
				mv.setViewName("/notice/noticeInfo2");
			}
			return mv;
		}
		return mv;
	}
	
	/**
	 * 回复问题
	 * @Title: replyQuestion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param replyQuestion
	 * @param noticeId
	 * @param userId
	 * @param stauts
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/replyQuestion.do")
	@ResponseBody
	public Map<String,Object> replyQuestion(HttpServletRequest request,HttpServletResponse resp,String replyQuestion,String noticeId,String userId,String stauts) throws IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NoticeWithBLOBs noticeWithBLOBs = new NoticeWithBLOBs();
		noticeWithBLOBs.setId(noticeId);
		noticeWithBLOBs.setReplyQuestion(replyQuestion);
		noticeWithBLOBs.setReplyHuman(userId);
		noticeWithBLOBs.setStatus(Integer.valueOf(stauts));
		noticeWithBLOBs.setCompletetime(sdf.format(new Date()));
		int count = noticeService.updateNoticeWithBLOBs(noticeWithBLOBs);
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0 ){
		    /** Create a data model */
		    Map<String, Object> mapTemplateData = new HashMap<String, Object>();
		    mapTemplateData.put( "notice", noticeWithBLOBs );
		    mapTemplateData.put( "replyTime", sdf.format(new Date()));
		    String temp = String.valueOf(new Date().getTime());
		    String htmlFilePath = request.getServletContext().getRealPath("/html/user_question/");
		    String htmlFileName = temp+".html";
		    FreeMarkerTemplateUtil.geneHtmlFile(request, mapTemplateData, htmlFilePath, "user_question.ftl", htmlFileName );
      		String realPath = htmlFilePath + htmlFileName;
			replyMsg("<a href="+realPath+">你的问题已经回复，请点击查看</a>", noticeWithBLOBs.getWxUserId(), resp);
			
			map.put("message", "回复成功!");
			return map;
		}else{
			map.put("message", "回复失败!");
			return map;
		}
	}
	
	
	/**
	 * 转交其它部门
	 * @Title: changeOther
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param noticeId
	 * @param stauts
	 * @return
	 */
	@RequestMapping("/changeOther.do")
	@ResponseBody
	public Map<String,Object> changeOther(String noticeId,String stauts){
		NoticeWithBLOBs noticeWithBLOBs = new NoticeWithBLOBs();
		noticeWithBLOBs.setId(noticeId);
		noticeWithBLOBs.setStatus(Integer.valueOf(stauts));
		int count = noticeService.updateNoticeWithBLOBs(noticeWithBLOBs);
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0 ){
			map.put("message", "转交成功!");
			return map;
		}else{
			map.put("message", "转交失败!");
			return map;
		}
	}
	
	/**
	 * 图片列表
	 * @Title: getImgNameList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param fbId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getImgNameList.do")
	@ResponseBody
	public List<String> getImgNameList(String id,HttpServletRequest request){
		NoticeWithBLOBs noticeWithBLOBs = noticeService.findById(id);
		String[] names = noticeWithBLOBs.getWxUserPicname().split(",");
		List<String> list = Arrays.asList(names);
		List<String> newList = new ArrayList<>();
		String relativePath = request.getContextPath()+File.separator+"user_question"+File.separator;
		if(list.size()>0){
			for (String string : list) {
				String name = relativePath + string ;
				newList.add(name);
			}
		}
		return newList;
	}
}
