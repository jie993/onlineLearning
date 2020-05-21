package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.User;
import com.learn.model.userCourselearn;
import com.learn.model.userCourselearnKey;

public interface userCourselearnMapper {
    int deleteByPrimaryKey(userCourselearnKey key);

    int insert(userCourselearn record);

    int insertSelective(userCourselearn record);

    userCourselearn selectByPrimaryKey(userCourselearnKey key);

    int updateByPrimaryKeySelective(userCourselearn record);

    int updateByPrimaryKey(userCourselearn record);
    
   List<userCourselearn> queryByPage(userCourselearn record);
   
   List<userCourselearn> batchProcessList(List<Integer> id);
   
   List<User> selectAll(userCourselearn record);
   
   int foreachInsertList(List<userCourselearn> list);
   
   List<userCourselearn> queryByPage2(userCourselearn record);
   
   List<userCourselearn> selectByTeacher( Map<String, Object> map);
}