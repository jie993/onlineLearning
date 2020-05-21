package com.learn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.dao.userCourselearnMapper;
import com.learn.model.User;
import com.learn.model.courseInfo;
import com.learn.model.logSeevideo;
import com.learn.model.userCourselearn;
import com.learn.service.courseServ;
import com.learn.service.courseStudyServ;
import com.learn.service.logRecordServ;
import com.learn.service.userServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class courseStudyContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(userCourselearn.class);
	@Autowired
	private courseServ courseServ;
	@Autowired
	private courseStudyServ studyserv;
	@Autowired
	private userServ userserv;
	@Autowired
	private logRecordServ logserv;
	@RequestMapping(value = "study/addStuList")
	@ResponseBody
	public Object uploadStudyList(@RequestBody List<userCourselearn> list) {
		logger.info("批量录入学生到课程学习===================="); 
		ResponseData responsedata;
		 try{
			 studyserv.addStuListToLearn(list);        
			 responsedata = ResponseData.ok();
			 responsedata.putDataValue("info", "录入成功");
		    }catch(Exception e){
		    	responsedata =ResponseData.forbidden();
		    }
		return responsedata;
	}
	
	@RequestMapping(value = "study/addStuListByExcel")
	@ResponseBody
	public Object uploadStudyListByExcel(@RequestBody List<userCourselearn> list) {
		logger.info("excel批量录入学生到课程学习===================="); 
		ResponseData responsedata = null;
		List<Integer> useridlist =new ArrayList<Integer>();
		try{
			for(userCourselearn study:list) {
				useridlist.add(study.getUserid());
			}
			List<User> userlist = userserv.selectByUseridList(useridlist);
			if(userlist.size()!=list.size()) {
				responsedata.putDataValue("info", "录入失败，请检查数据是否有效");
				return responsedata;
			}else {
				 studyserv.addStuListToLearn(list);        
				 responsedata = ResponseData.ok();
				 responsedata.putDataValue("info", "录入成功");
			}
		    }catch(Exception e){
		    	responsedata =ResponseData.forbidden();
		    }
		return responsedata;
	}
	
	/**
	 * 
	 *Title: getStuCourseGrade
	 *Description: 获得学生的学习成绩----管理员
	 *2020年4月21日下午9:50:10
	 * @param userid
	 * @param courseid
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/student/getCourseGrade")
	@ResponseBody
	public Object getStuCourseGrade(
			@RequestParam( value = "userid",required = false)Integer userid,
			@RequestParam( value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit) {
		userCourselearn learn = new userCourselearn();
		if(userid != null) {
			learn.setUserid(userid);
		}
		if(courseid != null) {
			learn.setCourseid(courseid);
		}
		Map<String, Object> map = studyserv.selectStudy(learn,page,limit);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("learnlist"), SerializerFeature.DisableCircularReferenceDetect));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("list", clist);
		responseData.putDataValue("total", map.get("total"));
		return responseData;
	}
	
	@RequestMapping(value = "/student/getCourseGradeByTeacher")
	@ResponseBody
	public Object getCourseGradeByTeacher(
			@RequestParam( value = "userid",required = false)Integer userid,
			@RequestParam( value = "courseid",required = false)Integer courseid,
			@RequestParam( value = "teacherid",required = false)Integer teacherid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit) {
		Map<String, Object> map = studyserv.selectStudyInfoByTeacher(page, limit, userid, courseid, teacherid);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("learnlist"), SerializerFeature.DisableCircularReferenceDetect));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("list", clist);
		responseData.putDataValue("total", map.get("total"));
		return responseData;
	}
	
	@RequestMapping(value = "/student/getStuCourse")
	@ResponseBody
	public Object getStuCourse(
			@RequestParam(value = "userid",required=true)int userid,
			@RequestParam(value = "coursetitle",required=false)String coursetitle){
		logger.info("获得学生所学习的课程====================");   
		
		userCourselearn learn = new userCourselearn();
		learn.setUserid(userid);
		List<userCourselearn> list = studyserv.selectStudy(learn);
		List<Integer> courseids= new ArrayList<Integer>();
		
		for(int i =0;i<list.size();i++) {
			courseids.add(list.get(i).getCourseid());
		}
		List<courseInfo> courselist = courseServ.getCourseByCourseidList(courseids,coursetitle);
		
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(courselist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "student/updateStuStudyInfo")
	@ResponseBody
	public Object updateStuStudyInfo(@RequestBody userCourselearn learn) {
		ResponseData responsedata;
		 try{
			 studyserv.updateStudyInfo(learn);        
			 responsedata = ResponseData.ok();
			 responsedata.putDataValue("info", "成绩修改成功");
		    }catch(Exception e){
		    	responsedata =ResponseData.forbidden();
		    }
		return responsedata;
	}
	
	
	@RequestMapping(value = "/student/addStuStudyRecord")
	@ResponseBody
	public void addStuStudyRecord(@RequestBody logSeevideo logvideo) {
		logserv.addLogSeevideo(logvideo);
	}
	@RequestMapping(value = "/student/stuStudyRecord")
	@ResponseBody
	public Object stuStudyRecord(@RequestBody logSeevideo logvideo) {
		logSeevideo log = logserv.selectLogSeevideo(logvideo);
		if(log != null) {
			List<logSeevideo> list = new ArrayList<>();
			list.add(log);
			ResponseData responseData = ResponseData.ok();
			JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
			responseData.putDataValue("list", clist);
			return responseData;
		}else {
			List<logSeevideo> list = new ArrayList<>();
			list.add(logvideo);
			ResponseData responseData = ResponseData.ok();
			JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
			responseData.putDataValue("list", clist);
			return responseData;
		}
	}
	
	@RequestMapping(value = "/student/onlineTimeStudyRecord")
	@ResponseBody
	public Object onlineTimeStudyRecord(
			@RequestParam(value = "userid",required=true)Integer userid,
			@RequestParam(value = "courseid",required=true)Integer courseid,
			@RequestParam(value = "daynum",required=false)Integer daynum) {
		if(daynum == null) {
			daynum = 7;
		}
		List<Map<String,Object>> map1 = logserv.getRecentlyOnlineData(userid, daynum);
		List<Map<String,Object>> map2 = logserv.getRecentlyStudyData(userid, courseid, daynum,10);//此处的10为单位最短时间，看视频时的最短记录时间，单位秒
		ResponseData responseData = ResponseData.ok();
		JSONArray clist1 = JSONArray.fromObject(JSON.toJSONString(map1));
		JSONArray clist2 = JSONArray.fromObject(JSON.toJSONString(map2));
		responseData.putDataValue("onlineData", clist1);
		responseData.putDataValue("studyData", clist2);
		return responseData;
	}
	
}
