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
import com.learn.model.Announcement;
import com.learn.model.homeworkInfo;
import com.learn.model.messageCourse;
import com.learn.service.courseServ;
import com.learn.service.courseStudyServ;
import com.learn.service.messageServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class messageContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(messageCourse.class);
	@Autowired
	private messageServ messageserv;
	@Autowired
	private courseStudyServ studyserv;
	
	/**
	 * 
	 *Title: addHomework
	 *Description: ���һ��������Ϣ
	 *2020��4��5������3:22:22
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/course/addMessage")
	@ResponseBody
	public Object addHomework(@RequestBody messageCourse message){
		logger.info("������Ϣ!!!====================");   
		if(messageserv.addMessage(message)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "��ӳɹ�");
			responseData.putDataValue("messageid", message.getMessageid());
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 
	 *Title: selectHomework
	 *Description: ��ʦ��ѯ�����Ŀ�����Ϣ
	 *2020��4��5������3:22:39
	 * @param userid
	 * @param page
	 * @param limit
	 * @param courseid
	 * @param messagetitle
	 * @return
	 */
	@RequestMapping(value = "/course/selectMessageByTeacher")
	@ResponseBody
	public Object selectHomework(@RequestParam( value = "userid",required = true)int userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "messagetitle",required = false)String messagetitle){
		messageCourse message = new messageCourse();
		if(!"".equals(messagetitle)) {
			message.setMessagetitle(messagetitle);
		}
		if(courseid!=null) {
			message.setCourseid(courseid);
		}
		message.setUserid(userid);
		Map<String, Object>map = messageserv.selectMessage(message, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("messagelist"),SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 
	 *Title: selectHomeworkByStu
	 *Description: ѧ����ѯ��Ϣ
	 *2020��4��5������3:23:07
	 * @param userid
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/course/selectMessageByStu")
	@ResponseBody
	public Object selectHomeworkByStu(
			@RequestParam(value = "userid",required = true)Integer userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit){
		List<Integer> cidlist = studyserv.selectCourseidByUserid(userid);
		Map<String, Object>map = messageserv.selectMessageByStu(cidlist, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("messagelist"),SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	/**
	 * 
	 *Title: updateMessage
	 *Description: ����Ϣ���и��²���
	 *2020��4��5������3:23:25
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/course/updateMessage")
	@ResponseBody
	public Object updateMessage(@RequestBody messageCourse message){
		logger.info("�޸���Ϣ!!!====================");   
		if(messageserv.updateMessage(message)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "��Ϣ�޸ĳɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 
	 *Title: delectMessage
	 *Description: ɾ����Ϣ
	 *2020��4��5������3:23:53
	 * @param messageid
	 * @return
	 */
	@RequestMapping(value = "/course/delectMessage")
	@ResponseBody
	public Object delectMessage(@RequestParam("messageid")int messageid){
		logger.info("ɾ����Ϣ!!!====================");   
		if(messageserv.delectMessage(messageid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "��Ϣɾ���ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/course/addAnnouncement")
	@ResponseBody
	public Object addAnnouncement(@RequestBody Announcement anno){
		logger.info("���ӹ���!!!====================");   
		if(messageserv.addAnnouncement(anno)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "��ӳɹ�");
			responseData.putDataValue("announcement", anno);
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/course/selectAnnouncement")
	@ResponseBody
	public Object selectAnnouncement(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "annoutitle",required = false)String annoutitle){
		Announcement anno = new Announcement();
		if(!"".equals(annoutitle)) {
			anno.setAnnoutitle(annoutitle);
		}
		Map<String, Object>map = messageserv.selectAnnouncement(anno, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("annolist"),SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	@RequestMapping(value = "/course/updateAnnouncement")
	@ResponseBody
	public Object updateAnnouncement(@RequestBody Announcement anno){
		if(messageserv.updateAnnouncement(anno)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "�����޸ĳɹ�");
			responseData.putDataValue("announcement", anno);
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/course/delectAnnouncement")
	@ResponseBody
	public Object delectAnnouncement(@RequestParam("annouid")int annouid){
		logger.info("ɾ����Ϣ!!!====================");   
		if(messageserv.delectAnnouncement(annouid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "����ɾ���ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
}
