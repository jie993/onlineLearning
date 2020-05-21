package com.learn.dao;

import java.util.List;
import com.learn.model.logSearch;

public interface logSearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logSearch record);

    int insertSelective(logSearch record);

    logSearch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logSearch record);

    int updateByPrimaryKey(logSearch record);
    
    List<logSearch> queryByPage(logSearch record);
}