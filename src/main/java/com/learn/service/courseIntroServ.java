package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.dao.courseIntroMapper;
import com.learn.dao.courseKnowledgepointMapper;
import com.learn.model.courseIntro;
import com.learn.model.courseKnowledgepoint;

@Service
public class courseIntroServ {
	@Autowired
	private courseIntroMapper cintrodao;
	@Autowired
	private courseKnowledgepointMapper cKnowledgepointdao;

	public boolean insertCourseIntro(courseIntro intro) {
		if (cintrodao.insertSelective(intro) > 0) {
			return true;
		}
		return false;
	}

	public boolean updateCourseIntro(courseIntro intro) {
		if (cintrodao.updateByPrimaryKeySelective(intro) > 0) {
			return true;
		}
		return false;
	}

	public List<courseIntro> selectCourseIntro(courseIntro intro) {
		List<courseIntro> list = cintrodao.queryByPage(intro);
		return list;
	}

	// 获取课程的知识点
	public List<courseKnowledgepoint> getCourseKPonit(courseKnowledgepoint courseKPonit) {
		List<courseKnowledgepoint> kplist = cKnowledgepointdao.queryByPage(courseKPonit);
		return kplist;
	}

	// 添加课程的知识点
	public boolean addCourseKPonit(List<courseKnowledgepoint> list) {
		int i = cKnowledgepointdao.foreachInsertList(list);
		if (i > 0) {
			return true;
		}
		return false;
	}
	
	//删除知识点
	public boolean delectCourseKPonit(Integer courseid) {
		int i = cKnowledgepointdao.deleteByCourseId(courseid);
		if (i > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateWeightValue(Integer courseid,Integer number) {
		Map<String,Object> map = new HashMap<>();
		map.put("courseid", courseid);
		map.put("number", number);
		int i = cKnowledgepointdao.updateWeightValue(map);
		if (i > 0) {
			return true;
		}
		return false;
	}
}
