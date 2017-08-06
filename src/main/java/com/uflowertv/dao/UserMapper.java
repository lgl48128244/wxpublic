package com.uflowertv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uflowertv.model.User;
import com.uflowertv.model.UserExample;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.user.dao.UserMapper     
 * 创建人：liguoliang 
 * 创建时间：2016年8月18日 上午9:30:06   
 * 修改人：
 * 修改时间：2016年8月18日 上午9:30:06   
 * 修改备注：   
 * @version   V1.0
 */
public interface UserMapper {
	
	//登录
	User login(User user);
	//通过邮件查询用户
	User findByEmail(String email);
	
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}