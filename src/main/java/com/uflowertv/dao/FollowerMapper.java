package com.uflowertv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uflowertv.model.Follower;
import com.uflowertv.model.FollowerExample;

public interface FollowerMapper {
    int countByExample(FollowerExample example);

    int deleteByExample(FollowerExample example);

    int deleteByPrimaryKey(String openid);

    int insert(Follower record);

    int insertSelective(Follower record);

    List<Follower> selectByExample(FollowerExample example);

    Follower selectByPrimaryKey(String openid);

    int updateByExampleSelective(@Param("record") Follower record, @Param("example") FollowerExample example);

    int updateByExample(@Param("record") Follower record, @Param("example") FollowerExample example);

    int updateByPrimaryKeySelective(Follower record);

    int updateByPrimaryKey(Follower record);
}