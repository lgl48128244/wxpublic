package com.uflowertv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uflowertv.commons.BaseService;
import com.uflowertv.dao.FollowerMapper;
import com.uflowertv.model.Follower;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：获取微信用户业务功能
 * 类名称：com.uflowertv.user.wxuser.service.FollowerService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月19日 上午10:49:58   
 * 修改人：
 * 修改时间：2016年8月19日 上午10:49:58   
 * 修改备注：   
 * @version   V1.0
 */
@Service
public class FollowerService extends BaseService{

	@Autowired
	private FollowerMapper followerMapper;
	
	/**
	 * 获取微信个人信息
	 * @Title: getFollower
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openId
	 * @return
	 */
	public Follower getFollower(String openId){
		log.info("获取微信个人信息");
		return  followerMapper.selectByPrimaryKey(openId);
	}
	/**
	 * 更新微信个人信息
	 * @Title: updateFollower
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param follower
	 * @return
	 */
	public int updateFollower(Follower follower){
		log.info("更新微信个人信息");
		return followerMapper.updateByPrimaryKeySelective(follower);
	}
	
	/**
	 * 保存微信个人信息
	 * @Title: saveFollower
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param follower
	 * @return
	 */
	public int saveFollower(Follower follower){
		log.info("保存微信个人信息");
		return followerMapper.insert(follower);
	}
}
