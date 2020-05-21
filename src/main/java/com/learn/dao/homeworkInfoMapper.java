package com.learn.dao;

import java.util.List;

import com.learn.model.homeworkInfo;
import com.learn.model.messageCourse;

public interface homeworkInfoMapper {
    int deleteByPrimaryKey(Integer wid);

    int insert(homeworkInfo record);

    int insertSelective(homeworkInfo record);

    homeworkInfo selectByPrimaryKey(Integer wid);

    int updateByPrimaryKeySelective(homeworkInfo record);

    int updateByPrimaryKeyWithBLOBs(homeworkInfo record);

    int updateByPrimaryKey(homeworkInfo record);
    
    List<homeworkInfo> queryByPage(homeworkInfo record);

}