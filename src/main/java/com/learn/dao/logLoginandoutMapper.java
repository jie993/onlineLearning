package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.logLoginandout;

public interface logLoginandoutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logLoginandout record);

    int insertSelective(logLoginandout record);

    logLoginandout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logLoginandout record);

    int updateByPrimaryKey(logLoginandout record);
    
    List<logLoginandout> queryByPage(logLoginandout record);
    
    int updateLogoutDate(logLoginandout record);
    
    List<logLoginandout>selectByDate(Map<String,Object> map);
    
    List<Map<String,Object>> selectOnlineTime(Map<String,Object> map);
}