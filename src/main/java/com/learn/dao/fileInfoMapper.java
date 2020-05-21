package com.learn.dao;

import java.util.List;

import com.learn.model.fileInfo;
import com.learn.model.homeworkCommit;

public interface fileInfoMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(fileInfo record);

    int insertSelective(fileInfo record);

    fileInfo selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(fileInfo record);

    int updateByPrimaryKey(fileInfo record);
    
    List<fileInfo> queryByPage(fileInfo record);
    
    int insertSelectiveReturnId(fileInfo record);
}