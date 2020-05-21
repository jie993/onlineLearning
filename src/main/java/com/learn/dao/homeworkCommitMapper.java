package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.homeworkCommit;
import com.learn.model.homeworkCommitKey;

public interface homeworkCommitMapper {
    int deleteByPrimaryKey(homeworkCommitKey key);

    int insert(homeworkCommit record);

    int insertSelective(homeworkCommit record);

    homeworkCommit selectByPrimaryKey(homeworkCommitKey key);

    int updateByPrimaryKeySelective(homeworkCommit record);

    int updateByPrimaryKey(homeworkCommit record);
    
    List<homeworkCommit> queryByPage(homeworkCommit record);
    
    Map<String, Object> selectHomeworkScore(Map<String, Object> map); 
}