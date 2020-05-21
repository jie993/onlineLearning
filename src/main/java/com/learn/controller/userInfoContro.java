package com.learn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.User;
import com.learn.service.userServ;
import com.learn.utils.JWT;
import com.learn.utils.ResponseData;

import net.sf.json.JSONArray;

@Controller
public class userInfoContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(User.class);
	@Autowired
	private userServ UserInfoServ;
	
	@RequestMapping(value = "/search/teacher")
	@ResponseBody
	public Map<String, Object> searchTeacher() {       
		Map<String, Object> map = new HashMap<String, Object>();
        String role = "teacher";
        List<User> teacherList = UserInfoServ.selectUsersByRole(role);
        logger.info("查询教师信息成功==========");           	
        JSONArray clist = JSONArray.fromObject(JSON.toJSONString(teacherList));
        System.out.println(clist.toString());
		map.put("data", clist);
		map.put("code", 20000);
		map.put("message", "访问成功");
		return map;
	}
	
	@RequestMapping(value = "/search/userByuserid")
	@ResponseBody
	public Map<String, Object> searchUser(@RequestParam("teacherid")int userid) {       
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setUserid(userid);
        List<User> userList = UserInfoServ.selectUser(user);
        logger.info("查询教师信息==========通过userid");           	
        JSONArray userlist = JSONArray.fromObject(JSON.toJSONString(userList));
        System.out.println(userlist.toString());
		map.put("data", userlist);
		map.put("code", 20000);
		map.put("message", "访问成功");
		return map;
	}
	
	@RequestMapping(value = "/search/allUser")
	@ResponseBody
	public Object searchAllUser(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "name",required = false)String name,
			@RequestParam(value = "role",required = false)String role) {       
		User user = new User();
		if(!"".equals(name)) {
			user.setRealname(name);
		}
		if(!"".equals(role)) {
			user.setRole(role);
		}
		Map<String,Object> map = UserInfoServ.selectUser(user,page,limit);        	
        JSONArray alluserlist = JSONArray.fromObject(JSON.toJSONString(map.get("userlist")));
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", alluserlist);
		return responseData;
	}
	
	/******
	 * 2020.3.23
	 * 蒋嘉杰
	 * 功能用于分页查询用户的信息,模糊查询依据用户姓名
	 * @param page
	 * @param limit
	 * @param loginname
	 * @return
	 */
	@RequestMapping(value = "/search/getAllStudent")
	@ResponseBody
	public Object getAllStudent(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "name",required = false)String name) {
		logger.info("查询所有用户信息==========分页");
		User user = new User();
		user.setRole("student");
		if(!"".equals(name)) {
			user.setRealname(name);
		}
		Map<String,Object> map = UserInfoServ.selectUser(user,page,limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray alist = JSONArray.fromObject(JSON.toJSONString(map.get("userlist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", alist);
		return responseData;
	}
	
	@RequestMapping(value = "/search/searchStudent")
	@ResponseBody
	public Object searchStudent(
			@RequestParam(value = "name",required = true)String name) {
		User user = new User();
		user.setRole("student");
		if(!"".equals(name)) {
			user.setRealname(name);
		}
		List<User> ulist = UserInfoServ.selectUser(user);
		ResponseData responseData = ResponseData.ok();
		JSONArray alist = JSONArray.fromObject(JSON.toJSONString(ulist, SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("list", alist);
		return responseData;
	}
	
	@RequestMapping(value = "/search/searchUserById")
	@ResponseBody
	public Object searchUserById(
			@RequestParam(value = "userid",required = true)Integer userid) {
		User user = new User();
		user.setUserid(userid);
		List<User> ulist = UserInfoServ.selectUser(user);
		ResponseData responseData = ResponseData.ok();
		JSONArray alist = JSONArray.fromObject(JSON.toJSONString(ulist, SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("list", alist);
		return responseData;
	}
	
	@RequestMapping(value = "/user/updateUserInfo")
	@ResponseBody
	public Object updateUserInfo(@RequestBody User user) {
		ResponseData responseData;
		if(UserInfoServ.updateUserInfo(user)) {
			responseData = ResponseData.ok();
		}else {
			responseData = ResponseData.forbidden();
		}
		return responseData;
	}
	@RequestMapping(value = "/user/registerUserInfo")
	@ResponseBody
	public Object registerUserInfo(@RequestBody User user) {
		ResponseData responseData;
		User _user = new User();
		_user.setLoginname(user.getLoginname());
		if(UserInfoServ.selectUser(_user).size()>0) {
			responseData = ResponseData.ok();
			responseData.putDataValue("info", "用户名已存在");
		}else {
			UserInfoServ.addUserInfo(user);
			responseData = ResponseData.ok();
			responseData.putDataValue("info", "注册成功");
		}
		return responseData;
	}
}
