package com.uflowertv.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uflowertv.model.User;
import com.uflowertv.model.ValidationData;
import com.uflowertv.service.UserService;
import com.uflowertv.util.CipherUtil;
import com.uflowertv.util.GUIDUtil;
import com.uflowertv.util.Mail;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.user.controller.UserController     
 * 创建人：liguoliang 
 * 创建时间：2016年8月16日 下午2:11:22   
 * 修改人：
 * 修改时间：2016年8月16日 下午2:11:22   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 登录
	 * @Title: login
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String,Object> login(User user,HttpSession session){
		ValidationData data = new ValidationData();
		Map<String,Object> map = new HashMap<String,Object>();
		user.setPwd(CipherUtil.generator(user.getPwd()));
		User user2 = userService.findLogin(user);
		if(user2!=null){
			session.setAttribute("user", user2);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setId(user2.getId());
			user.setLogintime(sdf.format(new Date()));
			userService.update(user);
			map.put("code", 200);
			map.put("message", "登录成功");
			return map;
		}else{
			User user3 = userService.findByEmail(user.getEmail());
			if(user3 != null && !user3.getPwd().equals(user.getPwd())){
				data.setPwdMsg("密码错误");
				map.put("data", data);
				return map;
			}else {
				data.setEmialMsg("该邮箱还没有被注册");
				map.put("data", data);
				return map;
			}
		}
	}
	
	
	/**
	 * 注销
	 * @Title: logout
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public Map<String,Object> logout(HttpSession session){
		session.invalidate();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", true);
		return map;
	}
	
	
	/**
	 * 注册
	 * @Title: reg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @param passwd
	 * @return
	 */
	@RequestMapping("/reg.do")
	@ResponseBody
	public Map<String,Object> reg(User user,String passwd){
		Map<String,Object> map = new HashMap<String,Object>();
		ValidationData data = new ValidationData();
		User user2 = userService.findByEmail(user.getEmail());
		if(user2 != null){
			data.setEmialMsg("该邮箱已被注册");
			map.put("data", data);
			return map;
		}else{//将密码加密保存
			user.setPwd(CipherUtil.generator(passwd));
			int count = userService.saveReg(user);
			if(count > 0){
				map.put("code", 200);
				map.put("message", "注册成功");
				return map;
			}else{
				map.put("code", 200);
				map.put("message", "注册失败，请联系管理员");
				return map;
			}
		}
	}
	
	
	/**
	 * 更新密码
	 * @Title: update
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param uId
	 * @param uPwd
	 * @return
	 */
	@RequestMapping("/aftUpdatePwd.do")
	@ResponseBody
	public Map<String,Object> update(String uId, String uPwd) {
		Map<String,Object> map = new HashMap<String,Object>();
		User u = userService.findById(uId);
		if(u != null){
			if(CipherUtil.validate(u.getPwd(), uPwd)){
				map.put("message", "不能与近期密码相同");
				return map;
			}else{
				u.setPwd(CipherUtil.generator(uPwd));
				int count =userService.update(u);
				if(count > 0){
					map.put("code", 200);
					map.put("message", "密码修改成功，请重新登录！");
					return map;
				}else{
					map.put("code", 200);
					map.put("message", "密码修改失败，请联系管理员");
					return map;
				}
			}
		}else{
			map.put("code", 200);
			map.put("message", "密码修改失败，用户不存在");
			return map;
		}
	}
	
	/**
	 * 发送邮件链接
	 * @Title: forget_password
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param email
	 * @return
	 */
	@RequestMapping("/forget_password.do")
	@ResponseBody
	public Map<String,Object> befPwd(HttpServletRequest request,String email){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.findByEmail(email);
		ValidationData data = new ValidationData();
		if(user == null){
			data.setEmialMsg("该邮箱不存在");
			map.put("data", data);
			return map;
		}else{
		 	String secretKey= GUIDUtil.get();  //密钥
            Date outDate = new Date(System.currentTimeMillis()+30*60*1000);//30分钟后过期
            long date = outDate.getTime()/1000*1000; //忽略毫秒数
            user.setValidatecode(secretKey);
            user.setOutdate(outDate);
            //保存到数据库
            int count = userService.update(user);   
            if(count > 0){
            	String key = email+"$"+date+"$"+secretKey;
            	
            	String path = request.getContextPath();
            	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            	String resetPassHref =  basePath+"user/reset_password.do?sid="+ CipherUtil.generator(key)+"&email="+email;
            	String emailContent = "请勿回复本邮件。点击下面的链接，重设密码。tips:本邮件超过30分钟链接将会失效，需要重新申请。<br/>"
            			+ "<a href="+resetPassHref +" target='_blank'>点击我重新设置密码</a>";
            	
            	Mail.setTo(user.getEmail());
            	Mail.setContent(emailContent);
            	boolean flag =	Mail.sendMail();
            	if(flag){
            		map.put("code", 200);
            		map.put("message", "邮件已发送");
            		return map;
            	}else{
            		map.put("code", 200);
            		map.put("message", "邮件发送失败");
            		return map;
            	}
            }else{
            	map.put("code", 200);
        		map.put("message", "程序出现异常，请联系管理员");
        		return map;
            }
		}
	}
	
	/**
	 * 验证邮件链接
	 * @Title: checkResetLink
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @param id
	 * @return
	 */
	@RequestMapping("/reset_password.do")
    public ModelAndView checkResetLink(String sid,String email){
		ModelAndView model = new ModelAndView("mailException");
        String msg = "";
        if(StringUtils.isBlank(sid) || StringUtils.isBlank(email)){
            msg="链接不完整,请重新生成";
            model.addObject("message",msg) ;
            return model;
        }else{
        	User user = userService.findByEmail(email);
            if(user == null){
                msg = "链接错误,无法找到匹配用户,请重新申请找回密码";
                model.addObject("message",msg) ;
                return model;
            }else{
            	Date outDate = user.getOutdate();
            	 //表示已经过期
                if(outDate.getTime() <= System.currentTimeMillis()){        
                    msg = "链接已经过期,请重新申请找回密码";
                    model.addObject("message",msg) ;
                    return model;
                }else{
                	//数字签名
                	String key = user.getEmail()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidatecode();          
                    if(!CipherUtil.validate(sid, key)) {
                        msg = "链接不正确,请重新申请";
                        model.addObject("message",msg) ;
                        return model;
                    }
                    //返回到修改密码的界面
                    model.setViewName("/pwd_update");  
                    return model;
                }
            }
        }
    }
	
	/**
	 * 修改密码
	 * @Title: updatePwd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param passwd
	 * @param email
	 * @return
	 */
	@RequestMapping("/befUpdatePwd.do")
	@ResponseBody
	public Map<String,Object> updatePwd(String passwd,String email){
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.findByEmail(email);
		ValidationData data = new ValidationData();
		if(user!=null && StringUtils.isNotBlank(passwd)){
			if(CipherUtil.validate(user.getPwd(), passwd)){
				data.setPwdMsg("不能与近期密码相同");
				map.put("data", data);
				return map;
			}else{
				user.setPwd(CipherUtil.generator(passwd));
				int count = userService.update(user);
				if(count > 0){
					map.put("code", 200);
					map.put("message", "密码更新成功");
					return map;
				}else{
					map.put("code", 200);
					map.put("message", "密码更新失败，请联系管理员");
					return map;
				}
			}
		}else{
			map.put("code", 200);
			map.put("message", "密码更新失败，用户不存在");
			return map;
		}
	}
}