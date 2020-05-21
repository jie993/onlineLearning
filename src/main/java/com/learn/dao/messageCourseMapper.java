package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.User;
import com.learn.model.messageCourse;
import com.learn.model.questionReply;
import com.learn.model.sectionCourseInfo;

public interface messageCourseMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(messageCourse record);

    int insertSelective(messageCourse record);

    messageCourse selectByPrimaryKey(Integer messageid);

    int updateByPrimaryKeySelective(messageCourse record);

    int updateByPrimaryKey(messageCourse record);
    
    List<messageCourse> queryByPage(messageCourse record);
    
    List<User> selectAll(messageCourse record);
    
    List<messageCourse>selectByList(Map<String,Object> map);

}