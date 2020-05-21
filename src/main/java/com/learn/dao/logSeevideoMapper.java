package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.logSeevideo;

public interface logSeevideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logSeevideo record);

    int insertSelective(logSeevideo record);

    logSeevideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logSeevideo record);

    int updateByPrimaryKey(logSeevideo record);
    
    List<logSeevideo> queryByPage(logSeevideo record);
    
    logSeevideo selectByUseridAndClassid(logSeevideo record);
    
    Map<String, Object> selectSeeVideoTime(Map<String, Object> map);

    List<Map<String, Object>> selectRecentlyData(Map<String, Object> map);
}