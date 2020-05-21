package com.learn.dao;

import java.util.List;

import com.learn.model.classInfo;
import com.learn.model.commentCourse;

public interface classInfoMapper {
    int deleteByPrimaryKey(Integer classid);

    int insert(classInfo record);

    int insertSelective(classInfo record);

    classInfo selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(classInfo record);

    int updateByPrimaryKey(classInfo record);
    
    List<classInfo> queryByPage(classInfo record);

    int insertSelectiveReturnId(classInfo record);
}