package com.learn.dao;

import java.util.List;

import com.learn.model.User;
import com.learn.model.classInfo;
import com.learn.model.sectionCourseInfo;
import com.learn.model.userCourselearn;

public interface sectionCourseInfoMapper {
    int deleteByPrimaryKey(Integer sectionid);

    int insert(sectionCourseInfo record);

    int insertSelective(sectionCourseInfo record);

    sectionCourseInfo selectByPrimaryKey(Integer sectionid);

    int updateByPrimaryKeySelective(sectionCourseInfo record);

    int updateByPrimaryKey(sectionCourseInfo record);
    
    List<sectionCourseInfo> queryByPage(sectionCourseInfo record);
    
    List<User> selectAll(sectionCourseInfo record);
    	
    int insertSelectiveReturnId(sectionCourseInfo record);
}