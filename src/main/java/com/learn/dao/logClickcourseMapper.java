package com.learn.dao;

import com.learn.model.logClickcourse;

public interface logClickcourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logClickcourse record);

    int insertSelective(logClickcourse record);

    logClickcourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logClickcourse record);

    int updateByPrimaryKey(logClickcourse record);
}