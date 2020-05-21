package com.learn.dao;

import java.util.List;

import com.learn.model.liveCourseAddresspool;

public interface liveCourseAddresspoolMapper {
   
	int deleteByPrimaryKey(Integer id);

    int insert(liveCourseAddresspool record);

    int insertSelective(liveCourseAddresspool record);

    liveCourseAddresspool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(liveCourseAddresspool record);

    int updateByPrimaryKey(liveCourseAddresspool record);
    
    List<liveCourseAddresspool> queryByPage(liveCourseAddresspool record);
    
    liveCourseAddresspool selectPrivateKey(liveCourseAddresspool record);
}