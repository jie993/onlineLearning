package com.learn.dao;

import java.util.List;

import com.learn.model.carousel;

public interface carouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(carousel record);

    int insertSelective(carousel record);

    carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(carousel record);

    int updateByPrimaryKey(carousel record);
    
    List<carousel> queryByPage(carousel record);
}