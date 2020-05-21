package com.learn.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.learn.model.classInfo;
import com.learn.model.courseInfo;
import com.learn.model.sectionCourseInfo;
import com.learn.service.classServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class classContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(classInfo.class);
	private static final Logger logger1 = org.apache.log4j.Logger.getLogger(sectionCourseInfo.class);
	@Autowired
	private classServ classserv;

	@RequestMapping(value = "/class/getClassSection")
	@ResponseBody
	public Object getClassSection(@RequestParam("courseid")int courseid){
		logger1.info("获得课程的章节====================");   
		sectionCourseInfo section = new sectionCourseInfo();
		section.setCourseid(courseid);
		List<sectionCourseInfo> slist =  classserv.selectSectionClass(section);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(slist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/class/addSection")
	@ResponseBody
	public Object addSection(@RequestBody sectionCourseInfo section){
		logger1.info("增加课程章节!!!====================");
		int i = classserv.addSectionClass(section);
		if(i >0) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "章节添加成功");
			responseData.putDataValue("sectionid", section.getSectionid());
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/class/updateClassSection")
	@ResponseBody
	public Object updateClassSection(@RequestBody sectionCourseInfo section){
		logger1.info("更新课程的章节====================");   
		
		if(classserv.updateSectionClass(section)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "章节更新成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/class/addClass")
	@ResponseBody
	public Object addClass(@RequestBody classInfo class1){
		logger.info("增加课时!!!====================");
		int i = classserv.addClass(class1);
		if(i>0) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "课时添加成功");
			responseData.putDataValue("classid", class1.getClassid());
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/class/updateClass")
	@ResponseBody
	public Object updateClass(@RequestBody classInfo class1){
		logger.info("更新课时!!!====================");   
		if(classserv.updateClass(class1)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "课时更新成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/class/getClass")
	@ResponseBody
	public Object getClass(@RequestParam("courseid")int courseid){
		logger.info("获得课时====================");   
		classInfo class1 = new classInfo();
		class1.setCourseid(courseid);
		List<classInfo> classlist =  classserv.selectClass(class1);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(classlist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/class/delectClass")
	@ResponseBody
	public Object delectClass(@RequestParam("classid")int classid){
		logger.info("删除课时====================");   
		if(classserv.detectClass(classid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "课时删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
		
	}
}
