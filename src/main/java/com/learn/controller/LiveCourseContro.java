package com.learn.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.liveCourseAddresspool;
import com.learn.model.liveCourseInfo;
import com.learn.service.LiveCourseServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class LiveCourseContro {
	@Autowired
	private LiveCourseServ livecourseserv;
	
	@RequestMapping(value = "/livecourse/addLiveCourse")
	@ResponseBody
	public Object addLiveCourse(@RequestBody liveCourseInfo live){  
		if(livecourseserv.insertLiveCourse(live)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "添加成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/livecourse/selectAllLiveCourse")
	@ResponseBody
	public Object selectAllLiveCourse(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "starttime",required = false)Date starttime,
			@RequestParam(value = "finishtime",required = false)Date finishtime){
		liveCourseInfo live = new liveCourseInfo();
		if(starttime != null) {
			live.setStarttime(starttime);
		}
		if(finishtime != null) {
			live.setFinshtime(finishtime);
		}
		if(courseid!=null) {
			live.setCourseid(courseid);
		}
		Map<String, Object>map = livecourseserv.selectLiveCourse(live, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("livelist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/livecourse/selectLiveCourseByTea")
	@ResponseBody
	public Object selectLiveCourseByTea(
			@RequestParam( value = "userid",required = true)int userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "starttime",required = false)Date starttime,
			@RequestParam(value = "finishtime",required = false)Date finishtime){
		liveCourseInfo live = new liveCourseInfo();
		if(starttime != null) {
			live.setStarttime(starttime);
		}
		if(finishtime != null) {
			live.setFinshtime(finishtime);
		}
		if(courseid!=null) {
			live.setCourseid(courseid);
		}
		live.setUserid(userid);
		Map<String, Object>map = livecourseserv.selectLiveCourse(live, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("livelist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/livecourse/selectLiveCourseByStu")
	@ResponseBody
	public Object selectLiveCourseByStu(
			@RequestParam( value = "userid",required = true)int userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "starttime",required = false)Date starttime,
			@RequestParam(value = "finishtime",required = false)Date finishtime){
		liveCourseInfo live = new liveCourseInfo();
		if(starttime != null) {
			live.setStarttime(starttime);
		}
		if(finishtime != null) {
			live.setFinshtime(finishtime);
		}
		if(courseid!=null) {
			live.setCourseid(courseid);
		}
		live.setUserid(userid);
		System.out.println(JSON.toJSONString(live));
		Map<String, Object>map = livecourseserv.selectLiveCourseByStu(live, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("livelist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/livecourse/selectCourseById")
	@ResponseBody
	public Object selectCourseById(@RequestParam("id")Integer id){
		Map<String, Object> map = livecourseserv.selectLiveCourseById(id);
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("liveurl", map.get("url"));
		responseData.putDataValue("live", map.get("live"));
		return responseData;
	}
	
	@RequestMapping(value = "/livecourse/updateLiveCourse")
	@ResponseBody
	public Object updateLiveCourse(@RequestBody liveCourseInfo live){   
		if(livecourseserv.updateLiveCourse(live)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "修改成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/livecourse/delectLiveCourse")
	@ResponseBody
	public Object delectLiveCourse(@RequestParam("id")Integer id){ 
		if(livecourseserv.delectLiveCourse(id)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/livecourse/addLiveAddress")
	@ResponseBody
	public Object addLiveAddress(@RequestBody liveCourseAddresspool Address){  
		if(livecourseserv.insertLiveAddress(Address)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("address", Address);
			responseData.putDataValue("info", "添加成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/livecourse/selectLiveAddressPrivateKey")
	@ResponseBody
	public Object selectLiveAddressPrivateKey(@RequestBody liveCourseAddresspool Address){  
		Address.setStatus(1);
		liveCourseAddresspool address = livecourseserv.getPrivateKey(Address);
		ResponseData responseData = ResponseData.ok();
		if(address!=null) {
		responseData.putDataValue("privateKey", address.getValue1());
		responseData.putDataValue("id", address.getId());
		}
		return responseData;
		
	}
	
	@RequestMapping(value = "/livecourse/selectLiveAddress")
	@ResponseBody
	public Object selectLiveAddress(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "createtime",required = false)Date createtime,
			@RequestParam(value = "finishtime",required = false)Date finishtime){
		liveCourseAddresspool address = new liveCourseAddresspool();
		if(createtime != null) {
			address.setCreatetime(createtime);
		}
		if(finishtime != null) {
			address.setFinishtime(finishtime);
		}
		Map<String, Object>map = livecourseserv.selectLiveAddress(address, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("addresslist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	@RequestMapping(value = "/livecourse/delectLiveAddress")
	@ResponseBody
	public Object delectLiveAddress(@RequestParam("id")Integer id){ 
		if(livecourseserv.delectLiveAddress(id)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	
}
