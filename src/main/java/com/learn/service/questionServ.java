package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.questionReplyMapper;
import com.learn.model.fileInfo;
import com.learn.model.questionReply;

@Service
public class questionServ {
	@Autowired
	private questionReplyMapper questiondao;
	
	/**
	 * 
	 *Title: addQuestion
	 *Description: 添加问题
	 *2020年4月4日上午6:12:15
	 * @param question
	 * @return
	 */
	public boolean addQuestion(questionReply question) {
		int i = questiondao.insertSelective(question);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 *Title: delectQuestion
	 *Description: 删除问题
	 *2020年4月4日上午6:12:32
	 * @param questionid
	 * @return
	 */
	public boolean delectQuestion(Integer questionid) {
		int i = questiondao.deleteByPrimaryKey(questionid);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 *Title: selectQuestion
	 *Description: 查询问题 教师
	 *2020年4月4日上午6:12:43
	 * @param question
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String, Object> selectQuestion(List<Integer>courseidList, questionReply question, int page, int limit){
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map2.put("courseidList", courseidList);
		map2.put("courseid", question.getCourseid());
		map2.put("questiontitle", question.getQuestiontitle());
		PageHelper.startPage(page, limit);
		List<questionReply> qlist = questiondao.selectByList(map2);
		PageInfo<questionReply> pageinfo = new PageInfo<>(qlist);
		long total = pageinfo.getTotal();
		map.put("questionlist", qlist);
		map.put("total", total);
		return map;
	}
	
	public Map<String, Object> selectQuestionByStu(questionReply question, int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<questionReply> qlist = questiondao.queryByPage(question);
		PageInfo<questionReply> pageinfo = new PageInfo<>(qlist);
		long total = pageinfo.getTotal();
		map.put("questionlist", qlist);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	 *Title: updateQuestion
	 *Description:更新问题 
	 *2020年4月4日上午6:13:12
	 * @param question
	 * @return
	 */
	public boolean updateQuestion(questionReply question) {
		int i = questiondao.updateByPrimaryKeySelective(question);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> getQestionNum(Integer userid,Integer courseid){
		questionReply question = new questionReply();
		question.setCourseid(courseid);
		question.setUserid(userid);
		Map<String,Object> map = questiondao.selectQestionNum(question);
		return map;
	}
}
