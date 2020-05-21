package com.learn.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.learn.model.courseInfo;
import com.learn.model.courseIntro;
import com.learn.model.courseKnowledgepoint;
import com.learn.service.courseIntroServ;
import com.learn.utils.ResponseData;
import com.learn.utils.UploadFile;

import net.sf.json.JSONArray;

@Controller
public class courseIntroContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(courseIntro.class);
	@Autowired
	private courseIntroServ introserv;
	
	
	@RequestMapping(value = "/course/getCourseIntro")
	@ResponseBody
	public Object getCourseIntro(@RequestParam("courseid")int courseid){
		logger.info("��ÿγ̽���====================");   
		courseIntro intro = new courseIntro();
		intro.setCourseid(courseid);
		List<courseIntro> introlist = introserv.selectCourseIntro(intro);
		ResponseData responseData = ResponseData.ok();
		JSONArray ilist = JSONArray.fromObject(JSON.toJSONString(introlist));
		responseData.putDataValue("list", ilist);
		return responseData;
	}
	
	@RequestMapping(value = "/teacher/addCourseIntro")
	@ResponseBody
	public Object addCourseIntro(@RequestBody courseIntro intro){
		logger.info("���ӿγ̽���====================");
		logger.info(intro.toString());
		ResponseData responseData = null;
		courseIntro intro1 = new courseIntro();//��ѯ�ÿγ��Ƿ��Ѵ��ڿγ̽���
		intro1.setCourseid(intro.getCourseid());
		List<courseIntro> introlist = introserv.selectCourseIntro(intro1);
		if(introlist.size()>0) {                //�Ѵ��ھ�ִ��update����
			intro.setCourseIntroid(introlist.get(0).getCourseIntroid());
			if(introserv.updateCourseIntro(intro)) {
				responseData = ResponseData.ok();
				responseData.putDataValue("info", "�༭�γ̽��ܳɹ�");
			}else {
				 responseData = ResponseData.forbidden();
				}
		}else {
			if(introserv.insertCourseIntro(intro)) {
				responseData = ResponseData.ok();
				responseData.putDataValue("info", "�༭�γ̽��ܳɹ�");
			}else {
			 responseData = ResponseData.forbidden();
			}
		}
		return responseData;
	}
	
	@RequestMapping(value = "/teacher/uploadCourseVideo")
	@ResponseBody
	public Object uploadCourseVideo(@RequestParam("file") MultipartFile file){
		logger.info("�ϴ��γ���Ƶ====================");   
		ResponseData responseData = ResponseData.ok();
		UploadFile uf = new UploadFile();
		String url = uf.uploadVedio(file);
		responseData.putDataValue("url",url);
		return responseData;
	}
	
	@RequestMapping(value = "/course/getCourseKnowledge")
	@ResponseBody
	public <T> Object getCourseKnowledge(@RequestParam("courseid")int courseid){
		logger.info("��ÿγ�֪ʶ��====================��װ��echart����");
		courseKnowledgepoint ckp = new courseKnowledgepoint();
		ckp.setCourseid(courseid);
		List<courseKnowledgepoint> ckplist = introserv.getCourseKPonit(ckp);
		
		ResponseData responseData = ResponseData.ok();
		JSONArray _ckplist = JSONArray.fromObject(JSON.toJSONString(ckplist));
		responseData.putDataValue("list", _ckplist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/addCourseKnowledge")
	@ResponseBody
	public Object addCourseKnowledge(@RequestBody List<courseKnowledgepoint> list){
		logger.info("���ӿγ�֪ʶ��====================");
		logger.info(list.toString());
		introserv.delectCourseKPonit(list.get(0).getCourseid());
		if(introserv.addCourseKPonit(list)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "���ӿγ�֪ʶ��ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.forbidden();
			return responseData;
		}
	}
	
	
}
