package com.uflowertv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.uflowertv.commons.BaseService;
import com.uflowertv.dao.InfoCentreMapper;
import com.uflowertv.model.InfoCentre;
import com.uflowertv.model.InfoCentreExample;

/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：用户反馈业务功能
 * 类名称：com.uflowertv.user.service.InfoCentreService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月18日 下午3:12:20   
 * 修改人：
 * 修改时间：2016年8月18日 下午3:12:20   
 * 修改备注：   
 * @version   V1.0
 */
@Service
public class InfoCentreService extends BaseService{

	@Autowired
	private InfoCentreMapper infoCentreMapper;
	/**
	 * 获取用户反馈信息列表
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> list(int pageNum,int pageSize){
		log.info("获取用户反馈信息列表");
		PageHelper.startPage(pageNum, pageSize);
		Map<String,Object> map = new HashMap<String, Object>();
		InfoCentreExample example = new InfoCentreExample();
		example.createCriteria().andIdIsNotNull();
		List<InfoCentre> list = infoCentreMapper.selectByExampleWithBLOBs(example);
		int count = infoCentreMapper.countByExample(example);
		if(list !=null && list.size()>0){
			map.put("rows", list);
			map.put("total", count);
			return map;
		}
		return null;
	}
}
