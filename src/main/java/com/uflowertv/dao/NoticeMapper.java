package com.uflowertv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uflowertv.model.Notice;
import com.uflowertv.model.NoticeExample;
import com.uflowertv.model.NoticeWithBLOBs;

public interface NoticeMapper {
    int countByExample(NoticeExample example);

    int deleteByExample(NoticeExample example);

    int deleteByPrimaryKey(String id);

    int insert(NoticeWithBLOBs record);

    int insertSelective(NoticeWithBLOBs record);

    List<NoticeWithBLOBs> selectByExampleWithBLOBs(NoticeExample example);

    List<Notice> selectByExample(NoticeExample example);

    NoticeWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NoticeWithBLOBs record, @Param("example") NoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") NoticeWithBLOBs record, @Param("example") NoticeExample example);

    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    int updateByPrimaryKeySelective(NoticeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NoticeWithBLOBs record);

    int updateByPrimaryKey(Notice record);
}