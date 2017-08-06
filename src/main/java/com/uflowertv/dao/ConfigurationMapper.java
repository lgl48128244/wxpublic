package com.uflowertv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uflowertv.model.Configuration;
import com.uflowertv.model.ConfigurationExample;

public interface ConfigurationMapper {
    int countByExample(ConfigurationExample example);

    int deleteByExample(ConfigurationExample example);

    int deleteByPrimaryKey(String id);

    int insert(Configuration record);

    int insertSelective(Configuration record);

    List<Configuration> selectByExampleWithBLOBs(ConfigurationExample example);

    List<Configuration> selectByExample(ConfigurationExample example);

    Configuration selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Configuration record, @Param("example") ConfigurationExample example);

    int updateByExampleWithBLOBs(@Param("record") Configuration record, @Param("example") ConfigurationExample example);

    int updateByExample(@Param("record") Configuration record, @Param("example") ConfigurationExample example);

    int updateByPrimaryKeySelective(Configuration record);

    int updateByPrimaryKeyWithBLOBs(Configuration record);

    int updateByPrimaryKey(Configuration record);
}