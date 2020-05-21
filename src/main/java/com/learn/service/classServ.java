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
	
	//���ӿ�ʱ
	public int addClass(classInfo classinfo) {
		int i = classdao.insertSelectiveReturnId(classinfo);
		return i;
	}
	//��ѯ��ʱ
	public List<classInfo> selectClass(classInfo classinfo){
		List<classInfo> classlist = classdao.queryByPage(classinfo);
		return classlist;
	}
	
	//ɾ����ʱ
	public boolean detectClass(Integer classid) {
		int i = classdao.deleteByPrimaryKey(classid);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	//�޸Ŀ�ʱ
	public boolean updateClass(classInfo classinfo) {
		if(classdao.updateByPrimaryKeySelective(classinfo)>0) {
			return true;
		}
		return false;
	}
	//���ӿγ��½�
	public int addSectionClass(sectionCourseInfo section) {
		int i= sectiondao.insertSelectiveReturnId(section);
		return i;
	}
	//��ѯ�γ��½�
	public List<sectionCourseInfo> selectSectionClass(sectionCourseInfo section) {
			List<sectionCourseInfo> slist = sectiondao.queryByPage(section);
			return slist;
		}
		
	//���¿γ��½�
	public boolean updateSectionClass(sectionCourseInfo section) {
		if(sectiondao.updateByPrimaryKeySelective(section)>0) {
			return true;
		}
		return false;
	}
			
}
