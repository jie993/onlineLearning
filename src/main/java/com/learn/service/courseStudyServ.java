package com.learn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.UserMapper;
import com.learn.dao.userCourselearnMapper;
import com.learn.model.homeworkCommit;
import com.learn.model.userCourselearn;

@Service
public class courseStudyServ {
	
	@Autowired
	private userCourselearnMapper studydao;
	
	//增加课程的学习信息
	public boolean addCourseStudy(userCourselearn study) {
		if(studydao.insertSelective(study)>0) {
			return true;
		}
		return false;
	}
	
	//查询学习信息
	public List<userCourselearn> selectStudy(userCourselearn study){
		List<userCourselearn>studylist =  studydao.queryByPage(study);
		return studylist;
	}
	
	/**
	 *Title: selectCourseidByUserid
	 *Description:根据学生的id查询到学生所学习的课程id 
	 *2020年4月5日上午2:43:29
	 * @param userid
	 * @return
	 */
		public List<Integer> selectCourseidByUserid(Integer userid){
			userCourselearn learn = new userCourselearn();
			List<Integer> courseidlist = new ArrayList<Integer>();
			learn.setUserid(userid);
			List<userCourselearn>studylist =  studydao.queryByPage(learn);
			for(userCourselearn study: studylist) {
				courseidlist.add(study.getCourseid());
			}
			return courseidlist;
		}
	
	//批量录入学生到课程中======增加事务回滚
	@Transactional(rollbackFor = Exception.class)
	public void addStuListToLearn(List<userCourselearn> studyList) {
		studydao.foreachInsertList(studyList);
	}
	
	/**
	 * 教师通课程的id查询该门课程下学生的学习信息
	 *Title: selectStudy
	 *Description: 
	 *2020年3月30日上午3:55:31
	 * @param study
	 * @param page
	 * @param limit
	 * @return
	 */
		public Map<String, Object> selectStudy(userCourselearn study,int page, int limit){
			Map<String, Object> map = new HashMap<String, Object>();
			PageHelper.startPage(page, limit);
			List<userCourselearn>studylist =  studydao.queryByPage2(study);
			PageInfo<userCourselearn> pageinfo = new PageInfo<>(studylist);
			long total = pageinfo.getTotal();
			map.put("learnlist", studylist);
			map.put("total", total);
			return map;
		}
		
	public boolean updateStudyInfo(userCourselearn study) {
		if(studydao.updateByPrimaryKeySelective(study)>0) {
			return true;
		}
		return false;
	}
	
	public Map<String, Object> selectStudyInfoByTeacher(int page, int limit,Integer userid,Integer courseid,Integer teacherid){
		PageHelper.startPage(page, limit);
		Map<String, Object> map = new HashMap<String,Object>();
		if(userid != null) {
			map.put("userid", userid);
		}
		if(courseid != null) {
			map.put("courseid", userid);
		}
		map.put("teacherid", userid);
		List<userCourselearn> slist = studydao.selectByTeacher(map);
		PageInfo<userCourselearn> pageinfo = new PageInfo<>(slist);
		long total = pageinfo.getTotal();
		map.put("learnlist", slist);
		map.put("total", total);
		return map;
	}
}
