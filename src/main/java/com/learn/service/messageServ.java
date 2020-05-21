package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.AnnouncementMapper;
import com.learn.dao.messageCourseMapper;
import com.learn.model.Announcement;
import com.learn.model.messageCourse;
import com.learn.model.questionReply;

@Service
public class messageServ {
	@Autowired
	private messageCourseMapper messagedao;
	@Autowired
	private AnnouncementMapper annodao;
	
	/**
	 * 
	 *Title: addMessage
	 *Description: 增加课内消息
	 *2020年4月5日下午2:42:12
	 * @param message
	 * @return
	 */
	public boolean addMessage(messageCourse message) {
		int i = messagedao.insertSelective(message);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *Title: selectMessage
	 *Description: 教师查询课内消息
	 *2020年4月5日下午2:42:18
	 * @param message
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String, Object> selectMessage(messageCourse message, int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<messageCourse> hlist = messagedao.queryByPage(message);
		PageInfo<messageCourse> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		int i = pageinfo.getList().size();
		map.put("messagelist", hlist);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	 *Title: selectMessageByStu
	 *Description: 学生查询课内消息
	 *2020年4月5日下午2:42:22
	 * @param courseidList
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String, Object> selectMessageByStu(List<Integer>courseidList, int page, int limit){
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map2.put("courseidList", courseidList);
		PageHelper.startPage(page, limit);
		List<messageCourse> mlist = messagedao.selectByList(map2);
		PageInfo<messageCourse> pageinfo = new PageInfo<>(mlist);
		long total = pageinfo.getTotal();
		map.put("messagelist", mlist);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	 *Title: updateMessage
	 *Description: 更新课内消息
	 *2020年4月5日下午2:42:26
	 * @param message
	 * @return
	 */
	public boolean updateMessage(messageCourse message){
		int i = messagedao.updateByPrimaryKeySelective(message);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *Title: delectMessage
	 *Description: 删除课内消息
	 *2020年4月5日下午2:42:31
	 * @param messageid
	 * @return
	 */
	public boolean delectMessage(Integer messageid){
		int i = messagedao.deleteByPrimaryKey(messageid);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean addAnnouncement(Announcement anno) {
		int i = annodao.insertSelective(anno);
		if(i>0) {
			return true;
		}
		return false;
	}
	public Map<String, Object> selectAnnouncement(Announcement anno, int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<Announcement> hlist = annodao.queryByPage(anno);
		PageInfo<Announcement> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("annolist", hlist);
		map.put("total", total);
		return map;
	}
	public boolean updateAnnouncement(Announcement anno){
		int i = annodao.updateByPrimaryKeySelective(anno);
		if(i>0) {
			return true;
		}
		return false;
	}
	public boolean delectAnnouncement(Integer annouid){
		int i = annodao.deleteByPrimaryKey(annouid);
		if(i>0) {
			return true;
		}
		return false;
	}
}
