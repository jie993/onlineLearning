package com.learn.controller;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learn.model.User;
import com.learn.model.courseInfo;
import com.learn.model.liveCourseAddresspool;
import com.learn.model.logClickcourse;
import com.learn.model.logDownloadfile;
import com.learn.model.logLoginandout;
import com.learn.model.logSearch;
import com.learn.model.logSeevideo;
import com.learn.model.userCourselearn;
import com.learn.service.LiveCourseServ;
import com.learn.service.courseIntroServ;
import com.learn.service.courseServ;
import com.learn.service.courseStudyServ;
import com.learn.service.logRecordServ;
import com.learn.utils.JWT;
import com.learn.utils.ResponseData;
@Controller
@Aspect
@Component
public class SysLogAop {
	@Autowired 
	private logRecordServ logServ;
	@Autowired
	private courseIntroServ introserv;
	@Autowired
	private LiveCourseServ liveserv;
	@Autowired
	private courseStudyServ studyserv;
	@Autowired
	private courseServ courseServ;
	private static final Logger log1 = org.apache.log4j.Logger.getLogger(logLoginandout.class);
	private static final Logger log2 = org.apache.log4j.Logger.getLogger(logSearch.class);
	private static final Logger log3 = org.apache.log4j.Logger.getLogger(logSeevideo.class);
	
	@Pointcut(value = "execution(* com.learn.controller.LoginAndOut.login*(..))")
	private void loginPointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.LoginAndOut.logout*())")
	private void logoutPointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.classContro.getClass(..))")
	private void getClassPointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.publicCourseContro.selectPublicCourse(..))")
	private void searchCoursePointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.courseContro.downloadFile(..))")
	private void downloadfilePointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.LiveCourseContro.selectLiveAddressPrivateKey(..))")
	private void getLivePrivateKeyPointCut() {}
	
	@Pointcut(value = "execution(* com.learn.controller.courseContro.getStudyRecord(..))")
	private void getStudyRecordPointCut() {}
	
	@Around(value="getStudyRecordPointCut()")
	@ResponseBody
	public Object  recordStudyInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        String param = JSON.toJSONString(paramObj);
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        userCourselearn study = new userCourselearn();
        String userid = param.substring(1, 2);
        String courseid = param.substring(3, 4);
        List<Map<String,Object>>map = (List<Map<String, Object>>) resultObj.getData().get("list");
        study.setCourseid(Integer.valueOf(courseid));
        study.setUserid(Integer.valueOf(userid));
        study.setDownloadfilenum(Integer.valueOf(map.get(0).get("downloadNum").toString()));
        study.setAnsquestionnum(Integer.valueOf(map.get(4).get("qestionNum").toString()));
        study.setCommentnum(Integer.valueOf(map.get(3).get("commentNum").toString()));
        study.setHomescore(Integer.valueOf(map.get(2).get("courseScore").toString()));
        study.setLearntime(Integer.valueOf(map.get(1).get("seetime").toString())/60);
        studyserv.updateStudyInfo(study);
        log1.info("*****************************************************************************************************************");
        log1.info("[访问时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[传入参数]>>>>>" + param);
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[返回参数]>>>>>" + resultObj.getData().get("token"));
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="loginPointCut()")
	@ResponseBody
	public Object  login(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        String param = JSON.toJSONString(paramObj);
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(resultObj.getData()!=null&&resultObj.getData().get("token") != null) {
        	User user = JWT.unsign(resultObj.getData().get("token").toString(), User.class);
        	log1.info("[Userid]>>>>>" + user.getUserid());
        	logLoginandout log = new logLoginandout();
        	log.setClientip(ip);
        	log.setLogindate(new Date());
        	log.setUserid(user.getUserid());
        	logServ.addLoginAndOutLog(log);
        }
        log1.info("*****************************************************************************************************************");
        log1.info("[访问时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[传入参数]>>>>>" + param);
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[返回参数]>>>>>" + resultObj.getData().get("token"));
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="logoutPointCut()")
	@ResponseBody
	public Object logoutAfter(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(request.getHeader("X-Token")!=null) {
        	User user = JWT.unsign(request.getHeader("X-Token").toString(), User.class);
        	if(null != user) {
        		log1.info("[Userid]>>>>>" + user.getUserid());
            	logLoginandout log = new logLoginandout();
            	log.setUserid(user.getUserid());
            	log.setLogoutdate(new Date());
            	logServ.updateLoginAndOutLog(log);
        	}
        }
        log1.info("*****************************************************************************************************************");
        log1.info("[退出时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="getClassPointCut()")
	@ResponseBody
	public Object clickCourseLog(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        
        String param = JSON.toJSONString(paramObj);
        
        String courseid = param.substring(1, param.length()-1);
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(request.getHeader("X-Token")!=null) {
        	User user = JWT.unsign(request.getHeader("X-Token").toString(), User.class);
        	if(null != user) {
        		log1.info("[Userid]>>>>>" + user.getUserid());
        		logClickcourse log = new logClickcourse();
            	log.setUserid(user.getUserid());
            	log.setClicktime(new Date());
            	log.setCourseid(Integer.parseInt(courseid));
            	logServ.addLogClickCourse(log);
            	introserv.updateWeightValue(Integer.parseInt(courseid), 1);
            	courseServ.updateAddStudyNum(Integer.parseInt(courseid));
        	}
        }
        log1.info("*****************************************************************************************************************");
        log1.info("[退出时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[传入参数]>>>>>" + courseid);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="searchCoursePointCut()")
	@ResponseBody
	public Object searchCourse(ProceedingJoinPoint joinPoint) throws Throwable {

		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        String param = JSON.toJSONString(paramObj);
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(request.getHeader("X-Token")!=null) {
        	User user = JWT.unsign(request.getHeader("X-Token").toString(), User.class);
        	if(null != user) {        		
        		List list1 = JSONObject.parseArray(param);
        		if(list1.get(3) != null && !"".equals(list1.get(3))) {
        			logSearch search = new logSearch();
            		search.setUserid(user.getUserid());
            		search.setSearchdate(new Date());
            		search.setSearchkey(list1.get(3).toString());
            		if(resultObj.getData().get("list").toString().length()>2) {
            			
            			log1.info("[resultObj]>>>>>" + "存在搜索内容");
            			search.setValue1("true");
            		}else {
            			log1.info("[resultObj]>>>>>" + "不存在搜索内容");
            			search.setValue1("false");
            		}
            		logServ.addLogSearch(search);
        		}
        		if(list1.get(3) != null && !"".equals(list1.get(3)) && resultObj.getData().get("list").toString().length()>2) {
        			List<courseInfo> list = JSONObject.parseArray(resultObj.getData().get("list").toString(),courseInfo.class);
        			for(courseInfo course:list) {
        				log1.info("[courseid]>>>>>" + course.getCourseid());
        				introserv.updateWeightValue(course.getCourseid(), 1);
        			}
        		}
        	}
        }
        log1.info("*****************************************************************************************************************");
        log1.info("[退出时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[传入参数]>>>>>" + param);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="downloadfilePointCut()")
	@ResponseBody
	public Object downloadfile(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        
        String param = JSON.toJSONString(paramObj);
        
        String fileid = param.substring(1, param.length()-1);
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(request.getHeader("X-Token")!=null) {
        	User user = JWT.unsign(request.getHeader("X-Token").toString(), User.class);
        	if(null != user) {        		
        		logDownloadfile log = new logDownloadfile();
        		log.setFileid(Integer.valueOf(fileid));
        		log.setUserid(user.getUserid());
        		log.setDownloadtime(new Date());
        		logServ.addLogdownloadfile(log);
        	}
        }
        log1.info("*****************************************************************************************************************");
        log1.info("[退出时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[传入参数]>>>>>" + param);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	@Around(value="getLivePrivateKeyPointCut()")
	@ResponseBody
	public Object updateLiveAdress(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求地址
        Object requestPath = request.getRequestURI();
        String ip = getIpAddr(request);
        //格式换开始时间
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //参数
        Object paramObj = joinPoint.getArgs();
        
        String param = JSON.toJSONString(paramObj);
        
        //返回结果
        ResponseData resultObj = (ResponseData) joinPoint.proceed();
        String result = JSON.toJSONString(resultObj);
        //获取切点方法对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = method.getName();
        if(resultObj.getData().get("privateKey") != null) {
        	liveCourseAddresspool liveaddress = new liveCourseAddresspool();
        	liveaddress.setId(Integer.valueOf(resultObj.getData().get("id").toString()));
        	liveaddress.setStatus(2);
        	liveserv.updateLiveAddress(liveaddress);
        }       
        log1.info("*****************************************************************************************************************");
        log1.info("[退出时间]>>>>>" + optTime);
        log1.info("[访问 IP]>>>>>" + ip);
        log1.info("[访问路由]>>>>>" + requestPath);
        log1.info("[传入参数]>>>>>" + param);
        log1.info("[访问方法]>>>>>" + className.concat(".").concat(methodName).concat("()"));
        log1.info("[返回参数]>>>>>" + result);
        log1.info("[耗费时间]>>>>>" + (System.currentTimeMillis() - beginTime) + " ms");
        log1.info("*****************************************************************************************************************\n");
        return resultObj;
	}
	
	/**
     * 获取IP地址
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
