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
	
	//���ӿγ̵�ѧϰ��Ϣ
	public boolean addCourseStudy(userCourselearn study) {
		if(studydao.insertSelective(study)>0) {
			return true;
		}
		return false;
	}
	
	//��ѯѧϰ��Ϣ
	public List<userCourselearn> selectStudy(userCourselearn study){
		List<userCourselearn>studylist =  studydao.queryByPage(study);
		return studylist;
	}
	
	/**
	 *Title: selectCourseidByUserid
	 *Description:����ѧ����id��ѯ��ѧ����ѧϰ�Ŀγ�id 
	 *2020��4��5������2:43:29
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
	
	//����¼��ѧ�����γ���======��������ع�
	@Transactional(rollbackFor = Exception.class)
	public void addStuListToLearn(List<userCourselearn> studyList) {
		studydao.foreachInsertList(studyList);
	}
	
	/**
	 * ��ʦͨ�γ̵�id��ѯ���ſγ���ѧ����ѧϰ��Ϣ
	 *Title: selectStudy
	 *Description: 
	 *2020��3��30������3:55:31
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
