package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.auditCourseMapper;
import com.learn.model.auditCourse;

@Service
public class courseAuditServ {
	@Autowired
	private auditCourseMapper auditCoursedao;
	
	//��ӿγ����
	public boolean addCourseAudit(auditCourse audit) {
		int i = auditCoursedao.insertSelective(audit);
		if(i>0) {
			return true;
		}
		return false;
	}
	//��ѯ�γ����=====��ҳ��ѯ
	public Map<String,Object> selectCourseAudit(auditCourse audit,int page, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		PageHelper.startPage(page,limit);
		List<auditCourse> alist = auditCoursedao.queryByPage(audit);
		PageInfo<auditCourse> pageinfo = new PageInfo<>(alist);
		long total = pageinfo.getTotal();
		map.put("auditlist", alist);
		map.put("total",total);
		return map;
	}
	
	//��ѯ�γ����
		public List<auditCourse> selectCourseAudit(auditCourse audit) {
			List<auditCourse> alist = auditCoursedao.queryByPage(audit);
			return alist;
		}
		
	
	//���¿γ����
	public boolean updataCourseAudit(auditCourse audit) {
		int i = auditCoursedao.updateByPrimaryKeySelective(audit);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public List<auditCourse> selectAll() {
		List<auditCourse> alist = auditCoursedao.selectAll();
		return alist;
	}
}
