package com.learn.dao;

import java.util.List;

import com.learn.model.courseIntro;
import com.learn.model.courseKnowledgepoint;

public interface courseIntroMapper {
    int deleteByPrimaryKey(Integer courseIntroid);

    int insert(courseIntro record);

    int insertSelective(courseIntro record);

    courseIntro selectByPrimaryKey(Integer courseIntroid);

    int updateByPrimaryKeySelective(courseIntro record);

    int updateByPrimaryKey(courseIntro record);
    
    List<courseIntro> queryByPage(courseIntro record);

}