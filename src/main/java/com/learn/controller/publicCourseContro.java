package com.learn.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.courseInfo;
import com.learn.service.courseServ;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class publicCourseContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(courseInfo.class);
	@Autowired
	private courseServ courseServ;
	
	/**
	 * 
	 *Title: selectPublicCourse
	 *Description: 查询所有公开课程
	 *2020年4月11日下午10:29:48
	 * @param page
	 * @param limit
	 * @param categoryid
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/course/selectPublicCourse")
	@ResponseBody
	public Object selectPublicCourse(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value ="categoryid",required = false)Integer categoryid,
			@RequestParam(value = "title",required = false)String title){
		courseInfo course = new courseInfo();
		if(!"".equals(title)) {
			course.setCoursetitle(title);
		}
		if(categoryid != null) {
			course.setCategoryid(categoryid);
		}
		course.setStatus(true);
		course.setIsfinish(false);
		course.setValue1("公开课");
		Map<String, Object>map =  courseServ.getCourse(course,page,limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("courselist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
}
