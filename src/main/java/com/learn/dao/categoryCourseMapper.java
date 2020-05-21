package com.learn.dao;

import java.util.List;

import com.learn.model.categoryCourse;
import com.learn.model.classInfo;

public interface categoryCourseMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(categoryCourse record);

    int insertSelective(categoryCourse record);

    categoryCourse selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(categoryCourse record);

    int updateByPrimaryKey(categoryCourse record);
    
    List<categoryCourse> queryByPage(categoryCourse record);

}