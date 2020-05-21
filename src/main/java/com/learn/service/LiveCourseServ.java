package com.learn.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.liveCourseAddresspoolMapper;
import com.learn.dao.liveCourseInfoMapper;
import com.learn.model.hottag;
import com.learn.model.liveCourseAddresspool;
import com.learn.model.liveCourseInfo;

@Service
public class LiveCourseServ {

	@Autowired
	private liveCourseInfoMapper livecoursedao;
	@Autowired
	private liveCourseAddresspoolMapper liveaddressdao;
	
	public boolean insertLiveCourse(liveCourseInfo liveinfo) {
		int i = livecoursedao.insert(liveinfo);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateLiveCourse(liveCourseInfo liveinfo) {
		int i = livecoursedao.updateByPrimaryKeySelective(liveinfo);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean delectLiveCourse(Integer id) {
		int i = livecoursedao.deleteByPrimaryKey(id);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> selectLiveCourseById(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		liveCourseInfo live = livecoursedao.selectByPrimaryKey(id);
		String url = live.getLiveurl()+'/'+live.getValue1();
		map.put("live", live);
		map.put("url", url);
		return map;
	}
	
	public Map<String,Object> selectLiveCourse(liveCourseInfo liveinfo,int page,int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<liveCourseInfo> hlist = livecoursedao.queryByPage(liveinfo);
		PageInfo<liveCourseInfo> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("livelist", hlist);
		map.put("total", total);
		return map;
	}
	
	public Map<String,Object> selectLiveCourseByStu(liveCourseInfo liveinfo,int page,int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<liveCourseInfo> hlist = livecoursedao.queryByPage2(liveinfo);
		PageInfo<liveCourseInfo> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("livelist", hlist);
		map.put("total", total);
		return map;
	}
	
	public boolean insertLiveAddress(liveCourseAddresspool liveaddress) {
		int i = liveaddressdao.insertSelective(liveaddress);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateLiveAddress(liveCourseAddresspool liveaddress) {
		int i = liveaddressdao.updateByPrimaryKeySelective(liveaddress);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean delectLiveAddress(Integer id) {
		int i = liveaddressdao.deleteByPrimaryKey(id);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> selectLiveAddress(liveCourseAddresspool liveaddress,int page,int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<liveCourseAddresspool> hlist = liveaddressdao.queryByPage(liveaddress);
		PageInfo<liveCourseAddresspool> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("addresslist", hlist);
		map.put("total", total);
		return map;
	}
	
	public liveCourseAddresspool getPrivateKey(liveCourseAddresspool liveaddress) {
		liveCourseAddresspool address = liveaddressdao.selectPrivateKey(liveaddress);
		return address;
	};
}
