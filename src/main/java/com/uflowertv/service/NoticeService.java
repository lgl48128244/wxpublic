package com.uflowertv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uflowertv.commons.BaseService;
import com.uflowertv.dao.NoticeMapper;
import com.uflowertv.dao.UserMapper;
import com.uflowertv.model.NoticeExample;
import com.uflowertv.model.NoticeExample.Criteria;
import com.uflowertv.model.NoticeWithBLOBs;
import com.uflowertv.model.User;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：问题反馈业务功能
 * 类名称：com.uflowertv.notice.service.NoticeService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月19日 下午3:28:21   
 * 修改人：
 * 修改时间：2016年8月19日 下午3:28:21   
 * 修改备注：   
 * @version   V1.0
 */
@Service
public class NoticeService extends BaseService{

	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 保存消息
	 * @Title: saveNotice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bloBs
	 * @return
	 */
	public int saveNotice(NoticeWithBLOBs bloBs){
		log.info("保存问题反馈");
		return noticeMapper.insert(bloBs);
	}
	
	
	/**
	 * 反馈问题列表
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param rows
	 * @param type
	 * @return
	 */
	public Map<String,Object> list(int page,int rows,int type,NoticeWithBLOBs noticeWithBLOBs){
		log.info("反馈问题列表");
		Map<String,Object> map = new HashMap<String,Object>();
		NoticeExample example = new NoticeExample();
		example.setOrderByClause("createtime");
		Criteria criteria = example.createCriteria().andIdIsNotNull().andStatusEqualTo(type);
		if(StringUtils.isNotBlank(noticeWithBLOBs.getWxQuestionType())){
			criteria.andWxQuestionTypeLike("%"+noticeWithBLOBs.getWxQuestionType()+"%");
		}
		if(StringUtils.isNotBlank(noticeWithBLOBs.getWxQuestionType())){
			criteria.andWxQuestionTypeLike("%"+noticeWithBLOBs.getWxUserQuestion()+"%");
		}
		List<NoticeWithBLOBs> list = noticeMapper.selectByExampleWithBLOBs(example);
		int count = noticeMapper.countByExample(example);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	/**
	 * 查看问题
	 * @Title: findById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	public NoticeWithBLOBs findById(String id) {
		log.info("查看问题");
		return noticeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 回复问题
	 * @Title: saveNoticeWithBLOBs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param noticeWithBLOBs
	 * @return
	 */
	public int updateNoticeWithBLOBs(NoticeWithBLOBs noticeWithBLOBs) {
		log.info("回复问题");
		return noticeMapper.updateByPrimaryKeySelective(noticeWithBLOBs);
	}

	/**
	 * 获取回复人昵称
	 * @Title: findByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userId
	 * @return
	 */
	public User findByUserId(String userId) {
		log.info("获取回复人");
		return userMapper.selectByPrimaryKey(userId);
	}
}
