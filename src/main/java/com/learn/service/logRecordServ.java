package com.learn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.logClickcourseMapper;
import com.learn.dao.logDownloadfileMapper;
import com.learn.dao.logLoginandoutMapper;
import com.learn.dao.logSearchMapper;
import com.learn.dao.logSeevideoMapper;
import com.learn.model.liveCourseInfo;
import com.learn.model.logClickcourse;
import com.learn.model.logDownloadfile;
import com.learn.model.logLoginandout;
import com.learn.model.logSearch;
import com.learn.model.logSeevideo;

@Service
public class logRecordServ {
	
	@Autowired
	private logLoginandoutMapper loginLogdao;
	@Autowired
	private logSearchMapper searchLogdao;
	@Autowired
	private logSeevideoMapper videoLogdao;
	@Autowired
	private logClickcourseMapper clickcourseLogdao;
	@Autowired
	private logDownloadfileMapper logfiledao;
	public boolean addLoginAndOutLog(logLoginandout log1) {
		int i = loginLogdao.insertSelective(log1);
		if(i>0) {
			return true;
		}
		return false;
		
	}
	public boolean updateLoginAndOutLog(logLoginandout log1) {
		int i = loginLogdao.updateLogoutDate(log1);
		if(i>0) {
			return true;
		}
		return false;
		
	}
	public List<logLoginandout> selectLoginAndOutLog(Date startDate,Date endDate,Integer userid) {
		Map<String,Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userid", userid);
		List<logLoginandout> list = loginLogdao.selectByDate(map);
		return list;	
	}
	
	public Map<String,Object> selectLoginAndOutLog(Date startDate,Date endDate,Integer page,Integer limit) {
		Map<String,Object> map = new HashMap<>();
		if(startDate != null) {
			map.put("startDate", startDate);
		}
		if(endDate != null) {
			map.put("endDate", endDate);
		}
		PageHelper.startPage(page, limit);
		List<logLoginandout> list = loginLogdao.selectByDate(map);
		PageInfo<logLoginandout> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();
		map.put("list", list);
		map.put("total", total);
		return map;	
	}
	
	public boolean addLogSearch(logSearch log1) {
		int i = searchLogdao.insertSelective(log1);
		if(i>0) {
			return true;
		}
		return false;
		
	}
	public List<logSearch> selectLogSearch(logSearch log1) {
		List<logSearch> list = null;
		return list;
		
	}
	public boolean addLogSeevideo(logSeevideo log1) {
		int i = videoLogdao.insertSelective(log1);
		if(i>0) {
			return true;
		}
		return false;
		
	}
	public logSeevideo selectLogSeevideo(logSeevideo log1) {
		logSeevideo log = videoLogdao.selectByUseridAndClassid(log1);
		return log;
		
	}
	
	public List<logSeevideo> selectLogVideo(logSeevideo log1) {
		List<logSeevideo> list = videoLogdao.queryByPage(log1);
		return list;	
	}
	
	public boolean addLogClickCourse(logClickcourse log1) {
		int i = clickcourseLogdao.insertSelective(log1);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean addLogdownloadfile(logDownloadfile log) {
		int i = logfiledao.insertSelective(log);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public List<logDownloadfile> selectLogdownloadfile(logDownloadfile log) {
		List<logDownloadfile> loglist = logfiledao.selectAll(log);
		return loglist;
	}
	
	public Map<String, Object> getFileDownloadNum(Integer userid,Integer courseid){
		Map<String,Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("courseid", courseid);
		Map<String, Object> map2 = logfiledao.selectDownloadNum(map);
		return map2;
	}
	
	public Map<String, Object> getSeeVideoTime(Integer userid,Integer... courseid){
		Map<String,Object> map = new HashMap<>();
		map.put("userid", userid);
		if(courseid != null) {
			map.put("courseid", courseid);
		}
		Map<String, Object> map2 = videoLogdao.selectSeeVideoTime(map);
		return map2;
	}
	
	public Map<String, Object> getSeeVideoTime(){
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> map2 = videoLogdao.selectSeeVideoTime(map);
		return map2;
	}
	
	public List<Map<String,Object>> getRecentlyStudyData(Integer userid,Integer courseid,Integer daynum,Integer itemTime){
		Map<String,Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("courseid", courseid);
		map.put("daynum", daynum);
		map.put("itemTime", itemTime);
		List<Map<String,Object>> list = videoLogdao.selectRecentlyData(map);
		return list;
	}
	
	public List<Map<String,Object>> getRecentlyOnlineData(Integer userid,Integer daynum){
		Map<String,Object> map = new HashMap<>();
		if(userid != null) {
			map.put("userid", userid);
		}
		map.put("daynum", daynum);
		List<Map<String,Object>> list = loginLogdao.selectOnlineTime(map);
		return list;
	}
	
	public List<Map<String,Object>> getRecentlyOnlineData(Integer daynum){
		Map<String,Object> map = new HashMap<>();
		map.put("daynum", daynum);
		List<Map<String,Object>> list = loginLogdao.selectOnlineTime(map);
		return list;
	}
}
