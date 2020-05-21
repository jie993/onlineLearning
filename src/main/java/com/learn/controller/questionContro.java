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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.commentCourse;
import com.learn.model.fileInfo;
import com.learn.model.questionReply;
import com.learn.service.courseServ;
import com.learn.service.questionServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class questionContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(questionReply.class);
	@Autowired
	private questionServ qserv;
	@Autowired
	private courseServ courseserv;
	
	@RequestMapping(value = "/course/addQuestion")
	@ResponseBody
	public Object addQuestion(@RequestBody questionReply question){
		logger.info("增加问题!!!====================");   
		if(qserv.addQuestion(question)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("questionid", question.getQuestionid());
			responseData.putDataValue("info", "添加成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}

	@RequestMapping(value = "/course/delectQuestion")
	@ResponseBody
	public Object delectQuestion(@RequestParam("questionid")int questionid){
		logger.info("删除问题!!!====================");   
		if(qserv.delectQuestion(questionid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/course/selectQuestion")
	@ResponseBody
	public Object selectQuestion(
			@RequestParam( value = "teacherid",required = true)int teacherid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "questiontitle",required = false)String questiontitle){
		logger.info("查询问题!!!====================");
		questionReply question = new questionReply();
		if(!"".equals(questiontitle)) {
			question.setQuestiontitle(questiontitle);
		}
		if(courseid!=null) {
			question.setCourseid(courseid);
		}
		List<Integer>cidList = courseserv.getCourseidByTeacherid(teacherid);
		
		Map<String, Object>map = qserv.selectQuestion(cidList, question, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("questionlist"),SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/selectQuestionByStu")
	@ResponseBody
	public Object selectQuestionByStu(
			@RequestParam( value = "courseid",required = true)int courseid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "questiontitle",required = false)String questiontitle){
		logger.info("查询问题!!!====================");
		questionReply question = new questionReply();
		if(!"".equals(questiontitle)) {
			question.setQuestiontitle(questiontitle);
		}
		question.setCourseid(courseid);
		Map<String, Object>map = qserv.selectQuestionByStu(question, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("questionlist"),SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	@RequestMapping(value = "/course/updateQuestion")
	@ResponseBody
	public Object updateQuestion(@RequestBody questionReply question){
		logger.info("更新问题!!!====================");   
		if(qserv.updateQuestion(question)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "问题回复成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
}
