package com.uflowertv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uflowertv.model.InfoCentre;
import com.uflowertv.model.InfoCentreExample;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.user.dao.InfoCentreMapper     
 * 创建人：liguoliang 
 * 创建时间：2016年8月18日 下午3:08:16   
 * 修改人：
 * 修改时间：2016年8月18日 下午3:08:16   
 * 修改备注：   
 * @version   V1.0
 */
public interface InfoCentreMapper {
    int countByExample(InfoCentreExample example);

    int deleteByExample(InfoCentreExample example);

    int deleteByPrimaryKey(String id);

    int insert(InfoCentre record);

    int insertSelective(InfoCentre record);

    List<InfoCentre> selectByExampleWithBLOBs(InfoCentreExample example);

    List<InfoCentre> selectByExample(InfoCentreExample example);

    InfoCentre selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InfoCentre record, @Param("example") InfoCentreExample example);

    int updateByExampleWithBLOBs(@Param("record") InfoCentre record, @Param("example") InfoCentreExample example);

    int updateByExample(@Param("record") InfoCentre record, @Param("example") InfoCentreExample example);

    int updateByPrimaryKeySelective(InfoCentre record);

    int updateByPrimaryKeyWithBLOBs(InfoCentre record);

    int updateByPrimaryKey(InfoCentre record);
}