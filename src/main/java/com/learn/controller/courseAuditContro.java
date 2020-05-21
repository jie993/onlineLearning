package com.learn.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.User;
import com.learn.model.auditCourse;
import com.learn.service.courseAuditServ;
import com.learn.utils.ResponseData;
import com.learn.utils.UploadFile;

import net.sf.json.JSONArray;

@Controller
public class courseAuditContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(auditCourse.class);
	@Autowired
	private courseAuditServ auditserv;
	

	@RequestMapping(value = "/audit/addCourseAudit")
	@ResponseBody
	public Object addCourseAudit(@RequestBody auditCourse audit){
		logger.info("添加课程的审核====================");
		if(auditserv.addCourseAudit(audit)) {
			
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "课程审核上传成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/audit/uploadAuditFile")
	@ResponseBody
	public Object uploadAuditFile(@RequestParam("file") MultipartFile file){
		logger.info("上传文件====================");
		ResponseData responseData = ResponseData.ok();
		UploadFile uf = new UploadFile();
		String url = uf.uploadFile(file);
		System.out.println(url);
		responseData.putDataValue("url",url);
		System.out.println(responseData.toString());
		return responseData;
	}

	@RequestMapping(value = "/audit/selectCourseAudit")
	@ResponseBody
	public Object selectCourseAudit(@RequestParam("teacherid") int id){
		logger.info("查询课程的审核====================通过教师ID");
		auditCourse audit = new auditCourse();
		audit.setUserid(id);
		List<auditCourse> alist = auditserv.selectCourseAudit(audit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(alist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/audit/selectAllAudit")
	@ResponseBody
	public Object selectAllAudit(@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "atitle",required = false)String atitle,
			@RequestParam(value = "value2",required = false)String value2,
			@RequestParam(value = "ispass",required = false)String ispass){
		logger.info("查询所有课程的审核====================");
		auditCourse audit = new auditCourse();
		if(!"".equals(atitle)) {
			audit.setAtitle(atitle);
		}
		if(!"".equals(value2)) {
			audit.setValue2(value2);
		}
		if("true".equals(ispass)) {
			audit.setIspass(true);
		}
		if("false".equals(ispass)){
			audit.setIspass(false);
		}
		Map<String,Object> map = auditserv.selectCourseAudit(audit,page,limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray alist = JSONArray.fromObject(JSON.toJSONString(map.get("auditlist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", alist);
		return responseData;
	}
	
	@RequestMapping(value = "/audit/selectAuditById")
	@ResponseBody
	public Object selectAuditById(@RequestParam("teacherid") int tid,@RequestParam("courseid") int cid){
		logger.info("查询课程的审核====================通过课程ID和教师ID");
		auditCourse audit = new auditCourse();
		audit.setCourseid(cid);
		audit.setUserid(tid);
		List<auditCourse> alist = auditserv.selectCourseAudit(audit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(alist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/audit/updateCourseAudit")
	@ResponseBody
	public Object updateCourseAudit(@RequestBody auditCourse audit){
		logger.info("更新课程的审核====================");
		if(auditserv.updataCourseAudit(audit)) {
			ResponseData responseData = ResponseData.ok();
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
}
