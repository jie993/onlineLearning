package com.learn.dao;

import java.util.List;

import com.learn.model.liveCourseInfo;

public interface liveCourseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(liveCourseInfo record);

    int insertSelective(liveCourseInfo record);

    liveCourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(liveCourseInfo record);

    int updateByPrimaryKey(liveCourseInfo record);
    
    List<liveCourseInfo> queryByPage(liveCourseInfo record);
    
    List<liveCourseInfo> queryByPage2(liveCourseInfo record);
}