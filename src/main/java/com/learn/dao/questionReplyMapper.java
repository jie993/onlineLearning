package com.learn.dao;

import java.util.List;
import java.util.Map;

import com.learn.model.User;
import com.learn.model.questionReply;

public interface questionReplyMapper {
    int deleteByPrimaryKey(Integer questionid);

    int insert(questionReply record);

    int insertSelective(questionReply record);

    questionReply selectByPrimaryKey(Integer questionid);

    int updateByPrimaryKeySelective(questionReply record);

    int updateByPrimaryKey(questionReply record);
    
    List<questionReply> queryByPage(questionReply record);
    
    Map<String,Object> selectQestionNum(questionReply record);
    
    List<questionReply>selectByList(Map<String,Object> map);
}