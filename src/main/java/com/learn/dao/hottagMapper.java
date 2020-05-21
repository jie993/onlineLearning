package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.hottag;

public interface hottagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(hottag record);

    int insertSelective(hottag record);

    hottag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(hottag record);

    int updateByPrimaryKey(hottag record);
    
    List<hottag> selectByDate(Map<String,Object> map);
    
    List<Map<String, Object>> selectHotTag(Integer tagnum);
    
    List<hottag> selectAllTag();
} 