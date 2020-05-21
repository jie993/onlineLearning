package com.learn.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.hottagMapper;
import com.learn.model.courseInfo;
import com.learn.model.hottag;
@Service
public class hottagServ {
	@Autowired
	private hottagMapper hottagdao;
	
	public boolean insertHottag(hottag hottag) {
		int i = hottagdao.insertSelective(hottag);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateHottag(hottag hottag) {
		int i = hottagdao.updateByPrimaryKeySelective(hottag);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean delectHottag(Integer id) {
		int i = hottagdao.deleteByPrimaryKey(id);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> selectByDate(Date startDate,Date endDate,int page,int limit){
		Map<String, Object> datemap = new HashMap<String, Object>();
		if(startDate != null) {
			datemap.put("startDate", startDate);
		}
		if(endDate != null) {
			datemap.put("endDate", endDate);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<hottag> hlist = hottagdao.selectByDate(datemap);
		PageInfo<hottag> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("hottaglist", hlist);
		map.put("total", total);
		return map;
	}
	
	public List<hottag> getAllHotTag(){
		List<hottag> list = hottagdao.selectAllTag();
		return list;
	}
	
	public Map<String,Object> selectHotTag(Integer tagnum){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String,Object>> hlist = hottagdao.selectHotTag(tagnum);
		map.put("hottaglist", hlist);
		return map;
	}
}
