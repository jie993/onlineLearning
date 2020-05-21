package com.learn.dao;

import java.util.List;

import com.learn.model.auditCourse;
import com.learn.model.categoryCourse;

public interface auditCourseMapper {
    int deleteByPrimaryKey(Integer auditid);

    int insert(auditCourse record);

    int insertSelective(auditCourse record);

    auditCourse selectByPrimaryKey(Integer auditid);

    int updateByPrimaryKeySelective(auditCourse record);

    int updateByPrimaryKey(auditCourse record);
    
    List<auditCourse> queryByPage(auditCourse record);
    
    List<auditCourse> selectAll();

}