package com.learn.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.model.carousel;
import com.learn.model.classInfo;
import com.learn.model.hottag;
import com.learn.model.sectionCourseInfo;
import com.learn.service.carouselServ;
import com.learn.service.hottagServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class carouselAndTagContro {
	@Autowired
	private carouselServ cserv;
	@Autowired
	private hottagServ hserv;
	
	@RequestMapping(value = "/publicCourse/addCarousel")
	@ResponseBody
	public Object addCarousel(@RequestBody carousel ca){
		if(cserv.addCarousel(ca)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "添加Carousel成功");
			responseData.putDataValue("carousel", ca);
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/publicCourse/updateCarousel")
	@ResponseBody
	public Object updateCarousel(@RequestBody carousel ca){
		if(cserv.updateCarousel(ca)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "更新成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/publicCourse/getCarousel")
	@ResponseBody
	public Object getCarousel(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit){
		carousel ca = new carousel();
		Map<String, Object>map =  cserv.selectCarousel(ca, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("carousellist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/publicCourse/showCarousel")
	@ResponseBody
	public Object showCarousel(){
		carousel ca = new carousel();
		ca.setType("1");
		List<carousel> carousellist =  cserv.selectCarousel(ca);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(carousellist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/publicCourse/delectCarousel")
	@ResponseBody
	public Object delectCarousel(@RequestParam("carouselid")Integer carouselid){   
		if(cserv.delectCarousel(carouselid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
		
	}
	
	@RequestMapping(value = "/publicCourse/generateHotTag")
	@ResponseBody
	public Object generateHotTag(@RequestParam("tagnum")Integer tagnum){   
		Map<String,Object> hmap =  hserv.selectHotTag(tagnum);
		JSONArray hlist = JSONArray.fromObject(JSON.toJSONString(hmap.get("hottaglist")));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("info", "生成标签成功");
		responseData.putDataValue("list", hlist);
		return responseData;
	}
	
	@RequestMapping(value = "/publicCourse/selectHottagByDate")
	@ResponseBody
	public Object selectHotByDate(@RequestParam(value = "page",required = true)int page,
								@RequestParam(value ="limit",required = true)int limit,
								@RequestParam(value ="startDate",required = false)Date startDate,
								@RequestParam(value ="endDate",required = false)Date endDate){
		Map<String,Object> map =  hserv.selectByDate(startDate, endDate, page, limit);
		JSONArray hlist = JSONArray.fromObject(JSON.toJSONString(map.get("hottaglist")));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", hlist);
		return responseData;
	}
	
	@RequestMapping(value = "/publicCourse/getAllHotTag")
	@ResponseBody
	public Object getAllHotTag(){
		List<hottag> hlist =  hserv.getAllHotTag();
		JSONArray list = JSONArray.fromObject(JSON.toJSONString(hlist));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("list", list);
		return responseData;
	}
	
	@RequestMapping(value = "/publicCourse/addHotTag")
	@ResponseBody
	public Object addHotTag(@RequestBody hottag tag){
		if(hserv.insertHottag(tag)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "添加hottag成功");
			responseData.putDataValue("hottag", tag);
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/publicCourse/updateHotTag")
	@ResponseBody
	public Object updateHotTag(@RequestBody hottag tag){
		if(hserv.updateHottag(tag)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "更新hottag成功");
			responseData.putDataValue("hottag", tag);
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	@RequestMapping(value = "/publicCourse/delectHotTag")
	@ResponseBody
	public Object delectHotTag(@RequestParam("hottagid")Integer hottagid){   
		if(hserv.delectHottag(hottagid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
}
