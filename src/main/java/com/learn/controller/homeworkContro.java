package com.learn.controller;

import java.util.HashMap;
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
import com.learn.model.homeworkCommit;
import com.learn.model.homeworkInfo;
import com.learn.service.homeworkServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class homeworkContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(homeworkInfo.class);
	private static final Logger logger2 = org.apache.log4j.Logger.getLogger(homeworkCommit.class);
	@Autowired
	private homeworkServ homeworkserv;
	
	/**
	 * 增加作业
	 *Title: addHomework
	 *Description: 
	 *2020年4月1日上午4:15:59
	 * @param homework
	 * @return
	 */
	@RequestMapping(value = "/course/addHomework")
	@ResponseBody
	public Object addHomework(@RequestBody homeworkInfo homework){
		logger.info("增加作业!!!====================");   
		if(homeworkserv.addHomework(homework)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "添加成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 教师查询作业
	 *Title: selectHomework
	 *Description: 
	 *2020年4月1日上午4:16:19
	 * @param userid
	 * @param page
	 * @param limit
	 * @param courseid
	 * @param wtitle
	 * @return
	 */
	@RequestMapping(value = "/course/selectHomeworkByTeacher")
	@ResponseBody
	public Object selectHomework(@RequestParam( value = "userid",required = true)int userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "wtitle",required = false)String wtitle){
		homeworkInfo homework = new homeworkInfo();
		if(!"".equals(wtitle)) {
			homework.setWtitle(wtitle);
		}
		if(courseid!=null) {
			homework.setCourseid(courseid);
		}
		homework.setUserid(userid);
		Map<String, Object>map = homeworkserv.selectHomework(homework, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("homeworklist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 学生查询作业
	 *Title: selectHomeworkByStu
	 *Description: 
	 *2020年4月1日上午4:16:39
	 * @param courseid
	 * @param wtitle
	 * @return
	 */
	@RequestMapping(value = "/course/selectHomeworkByStu")
	@ResponseBody
	public Object selectHomeworkByStu(
			@RequestParam(value = "courseid",required = true)Integer courseid,
			@RequestParam(value = "wtitle",required = false)String wtitle){
		homeworkInfo homework = new homeworkInfo();
		if(!"".equals(wtitle)) {
			homework.setWtitle(wtitle);
		}
		homework.setCourseid(courseid);
		List<homeworkInfo> hlist = homeworkserv.selectHomework(homework);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(hlist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 通过wid获得对应作业
	 *Title: selectHomeworkById
	 *Description: 
	 *2020年4月2日上午10:10:45
	 * @param wid
	 * @return
	 */
	@RequestMapping(value = "/course/selectHomeworkById")
	@ResponseBody
	public Object selectHomeworkById(@RequestParam(value = "wid")Integer wid){
		homeworkInfo homework = new homeworkInfo();
		homework.setWid(wid);
		List<homeworkInfo> hlist = homeworkserv.selectHomework(homework);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(hlist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	/**
	 * 教师修改作业
	 *Title: updateHomework
	 *Description: 
	 *2020年4月1日上午4:42:27
	 * @param homework
	 * @return
	 */
	@RequestMapping(value = "/course/updateHomework")
	@ResponseBody
	public Object updateHomework(@RequestBody homeworkInfo homework){
		logger.info("修改作业!!!====================");   
		if(homeworkserv.updateHomework(homework)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "作业修改成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 教师删除作业
	 *Title: delectHomework
	 *Description: 
	 *2020年4月1日上午4:42:44
	 * @param wid
	 * @return
	 */
	@RequestMapping(value = "/course/delectHomework")
	@ResponseBody
	public Object delectHomework(@RequestParam("wid")int wid){
		logger.info("删除作业!!!====================");   
		if(homeworkserv.delectHomework(wid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "作业删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 教师查询学生已提交的作业
	 *Title: selectCommitHomework
	 *Description: 
	 *2020年4月1日上午4:43:01
	 * @param page
	 * @param limit
	 * @param wid
	 * @return
	 */
	@RequestMapping(value = "/course/selectCommitHomework")
	@ResponseBody
	public Object selectCommitHomework(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "wid",required = true)Integer wid){
		homeworkCommit commithomework = new homeworkCommit();
		commithomework.setWid(wid);
		Map<String, Object>map = homeworkserv.selectCommitHomework(commithomework, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("commitlist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 学生查询自己提交的作业
	 *Title: selectCommitHomeworkByStu
	 *Description: 
	 *2020年4月1日上午4:43:16
	 * @param wid
	 * @param useid
	 * @return
	 */
	@RequestMapping(value = "/course/selectCommitHomeworkByStu")
	@ResponseBody
	public Object selectCommitHomeworkByStu(
			@RequestParam(value = "wid",required = true)int wid,
			@RequestParam(value ="userid",required = true)int userid){
		homeworkCommit commithomework = new homeworkCommit();
		commithomework.setWid(wid);
		commithomework.setUserid(userid);
		List<homeworkCommit> commitlsit = homeworkserv.selectCommitHomework(commithomework);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(commitlsit));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 学生提交作业
	 *Title: CommitHomeworkByStu
	 *Description: 提交作业前查询是否已提交过作业，若已提交过作业，则更新操作；
	 *2020年4月2日下午12:12:50
	 * @param commithomework
	 * @return
	 */
	@RequestMapping(value = "/course/commitHomeworkByStu")
	@ResponseBody
	public Object commitHomeworkByStu(@RequestBody homeworkCommit commithomework){
		homeworkCommit commithomework2 = new homeworkCommit();
		commithomework2.setUserid(commithomework.getUserid());
		commithomework2.setWid(commithomework.getWid());
		ResponseData responseData = null;
		try {
			List<homeworkCommit> commitlsit = homeworkserv.selectCommitHomework(commithomework2);
			if(commitlsit.size()>0) {
				homeworkserv.updateCommitHomework(commithomework);
			}else {
				homeworkserv.commitHomework(commithomework);
			}
			responseData = ResponseData.ok();
			JSONArray clist = JSONArray.fromObject(JSON.toJSONString(commitlsit));
			responseData.putDataValue("list", clist);
		} catch (Exception e) {
			responseData = ResponseData.forbidden();
		}
		return responseData;
	}
	
	/**
	 * 教师批改作业
	 *Title: updateCommitHomework
	 *Description: 
	 *2020年4月1日上午4:45:19
	 * @param commit
	 * @return
	 */
	@RequestMapping(value = "/course/updateCommitHomework")
	@ResponseBody
	public Object updateCommitHomework(@RequestBody homeworkCommit commit){
		logger.info("批改修改已提交作业!!!====================");   
		if(homeworkserv.updateCommitHomework(commit)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "作业批改成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
}
