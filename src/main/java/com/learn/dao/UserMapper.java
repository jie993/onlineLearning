package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.User;
import com.learn.model.userCourselearn;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByNameAndPsd(User record);
    
    List<User> queryByPage(User userParam);
    
    List<User> selectAll(User userParam);
    
    List<User> selectByUseridList(List<Integer> id);
    
    Map<String,Object> selectUserNumByRole(String role);
}