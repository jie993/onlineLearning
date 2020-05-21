package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.logDownloadfile;

public interface logDownloadfileMapper {
   
	int deleteByPrimaryKey(Integer id);

    int insert(logDownloadfile record);

    int insertSelective(logDownloadfile record);

    logDownloadfile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logDownloadfile record);

    int updateByPrimaryKey(logDownloadfile record);
    
    List<logDownloadfile> selectAll(logDownloadfile record);
    
    Map<String, Object> selectDownloadNum(Map<String, Object> map);
}