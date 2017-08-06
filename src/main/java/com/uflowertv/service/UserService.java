package com.uflowertv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uflowertv.commons.BaseService;
import com.uflowertv.dao.UserMapper;
import com.uflowertv.model.User;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.user.service.UserService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月16日 上午9:04:32   
 * 修改人：
 * 修改时间：2016年8月16日 上午9:04:32   
 * 修改备注：   
 * @version   V1.0
 */
@Service
public class UserService extends BaseService{

	@Autowired
	private UserMapper userMapper;
	
	public User findLogin(User user ){
		log.info("用户登录");
		return userMapper.login(user);
	}

	public int saveReg(User user) {
		log.info("用户注册");
		return userMapper.insert(user);
	}

	public User findById(String id) {
		log.info("通过ID查找用户");
		return userMapper.selectByPrimaryKey(id);
	}

	public User findByEmail(String email) {
		log.info("通过邮件查找用户");
		return userMapper.findByEmail(email);
	}

	public int update(User user) {
		log.info("更新用户");
		return userMapper.updateByPrimaryKeySelective(user);
		
	}
}
