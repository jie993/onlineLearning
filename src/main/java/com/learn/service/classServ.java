package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.dao.classInfoMapper;
import com.learn.dao.sectionCourseInfoMapper;
import com.learn.model.classInfo;
import com.learn.model.sectionCourseInfo;

@Service
public class classServ {
	@Autowired
	private classInfoMapper classdao;
	@Autowired
	private sectionCourseInfoMapper sectiondao;
	
	//增加课时
	public int addClass(classInfo classinfo) {
		int i = classdao.insertSelectiveReturnId(classinfo);
		return i;
	}
	//查询课时
	public List<classInfo> selectClass(classInfo classinfo){
		List<classInfo> classlist = classdao.queryByPage(classinfo);
		return classlist;
	}
	
	//删除课时
	public boolean detectClass(Integer classid) {
		int i = classdao.deleteByPrimaryKey(classid);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	//修改课时
	public boolean updateClass(classInfo classinfo) {
		if(classdao.updateByPrimaryKeySelective(classinfo)>0) {
			return true;
		}
		return false;
	}
	//增加课程章节
	public int addSectionClass(sectionCourseInfo section) {
		int i= sectiondao.insertSelectiveReturnId(section);
		return i;
	}
	//查询课程章节
	public List<sectionCourseInfo> selectSectionClass(sectionCourseInfo section) {
			List<sectionCourseInfo> slist = sectiondao.queryByPage(section);
			return slist;
		}
		
	//更新课程章节
	public boolean updateSectionClass(sectionCourseInfo section) {
		if(sectiondao.updateByPrimaryKeySelective(section)>0) {
			return true;
		}
		return false;
	}
			
}
