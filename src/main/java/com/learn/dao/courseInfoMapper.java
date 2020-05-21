package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.courseInfo;
import com.learn.model.courseIntro;

public interface courseInfoMapper {
    int deleteByPrimaryKey(Integer courseid);

    int insert(courseInfo record);

    int insertSelective(courseInfo record);

    courseInfo selectByPrimaryKey(Integer courseid);

    int updateByPrimaryKeySelective(courseInfo record);

    int updateByPrimaryKey(courseInfo record);
    
    //�й�ϵӳ�䣬��ѯ�γ̡���ʦ���γ̷���
    List<courseInfo> queryByPage(courseInfo record);
    
    //û�н��й�ϵӳ���
    List<courseInfo> queryByPage2(courseInfo record);
    
    List<courseInfo>selectByList(Map<String,Object> map);
    
    courseInfo selectByPrimaryKey2(Integer courseid);
    
    List<courseInfo>selectHotCourseByKeyword(String keyword);
    
    Map<String,Object> selectCourseNum();
    
    List<Map<String,Object>> selectStudyNumRank(Integer limit);
    
    int updateAddStudyNum(Integer courseid);
}