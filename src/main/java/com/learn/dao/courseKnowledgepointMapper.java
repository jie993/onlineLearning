package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.courseKnowledgepoint;
import com.learn.model.fileInfo;

public interface courseKnowledgepointMapper {
    int deleteByPrimaryKey(Integer kpintoid);

    int insert(courseKnowledgepoint record);

    int insertSelective(courseKnowledgepoint record);

    courseKnowledgepoint selectByPrimaryKey(Integer kpintoid);

    int updateByPrimaryKeySelective(courseKnowledgepoint record);

    int updateByPrimaryKey(courseKnowledgepoint record);
    
    List<courseKnowledgepoint> queryByPage(courseKnowledgepoint record);
    
    int foreachInsertList(List<courseKnowledgepoint> list);
    
    int deleteByCourseId(Integer courseid);
    
    int updateWeightValue(Map<String,Object> map);
}