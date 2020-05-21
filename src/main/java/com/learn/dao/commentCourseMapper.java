package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.commentCourse;
import com.learn.model.courseInfo;

public interface commentCourseMapper {
    int deleteByPrimaryKey(Integer commentid);

    int insert(commentCourse record);

    int insertSelective(commentCourse record);

    commentCourse selectByPrimaryKey(Integer commentid);

    int updateByPrimaryKeySelective(commentCourse record);

    int updateByPrimaryKey(commentCourse record);
    
    List<commentCourse> queryByPage(commentCourse record);
    
    int insertSelectiveReturnid(commentCourse record);
    
    Map<String, Object> selectCommentNum(commentCourse record);
}