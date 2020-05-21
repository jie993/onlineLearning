package com.learn.dao;

import java.util.List;

import com.learn.model.Announcement;
import com.learn.model.auditCourse;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer annouid);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer annouid);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
    
    List<Announcement> queryByPage(Announcement record);

}