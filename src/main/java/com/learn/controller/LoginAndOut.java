package com.learn.controller;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.learn.model.User;
import com.learn.service.logRecordServ;
import com.learn.service.userServ;
import com.learn.utils.JWT;
import com.learn.utils.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
@Controller
public class LoginAndOut {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(User.class);
	@Autowired
	private userServ loginserv;
	@Autowired
	private logRecordServ logserv;
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody User user) {       
		ResponseData responseData;
        User getuser = loginserv.login(user);
        if (getuser != null) {
        		logger.info("登录成功==========");
            	//登录成功
                //生成加密token
                String token = JWT.sign(getuser, 60L * 1000L * 30L);
                String token1 = "admin-token";
                //返回给客户端的信息
                responseData = ResponseData.ok();           
                responseData.putDataValue("token", token);
                System.out.println(responseData);
            } else {
                responseData = ResponseData.customerError("账号或密码有误");            
            }
        JSONArray ejson = JSONArray.fromObject(responseData);
        System.out.println(ejson);
		return responseData;
	}
	
	@RequestMapping(value = "/info")
	@ResponseBody
	public Object getInfo(@RequestParam("token") String token) {
		logger.info("查询用户信息成功==========");
		System.out.println(token);
		User user = JWT.unsign(token, User.class);
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue(user);
		System.out.println(responseData.toString());
		return responseData;
	}
	
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Object logout() {
		logger.info("退出成功==========");
		ResponseData responseData = ResponseData.ok();
		System.out.println(responseData.toString());
		return responseData;
	}
	
	@RequestMapping(value = "/getLoginAndOutLog")
	@ResponseBody
	public Object getLoginLog(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "startDate",required = false)Date startDate,
			@RequestParam(value ="endDate",required = false)Date endDate) {
		ResponseData responseData = ResponseData.ok();
		Map<String,Object> map = logserv.selectLoginAndOutLog(startDate, endDate, page, limit);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("list")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
}
