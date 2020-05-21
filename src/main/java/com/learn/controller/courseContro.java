package com.learn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.learn.model.categoryCourse;
import com.learn.model.commentCourse;
import com.learn.model.courseInfo;
import com.learn.model.courseIntro;
import com.learn.model.courseKnowledgepoint;
import com.learn.model.fileInfo;
import com.learn.model.logDownloadfile;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learn.model.User;
import com.learn.service.courseServ;
import com.learn.service.homeworkServ;
import com.learn.service.logRecordServ;
import com.learn.service.questionServ;
import com.learn.service.userServ;
import com.learn.utils.ResponseData;
import com.learn.utils.UploadFile;

import net.sf.json.JSONArray;

/**
 * @author 蒋嘉杰
 * 2020年3月30日上午12:45:09
 */
@Controller
public class courseContro {
	private static final Logger logger = org.apache.log4j.Logger.getLogger(courseInfo.class);
	@Autowired
	private courseServ courseServ;
	@Autowired
	private logRecordServ logrecordserv;
	@Autowired
	private homeworkServ homeworkserv;
	@Autowired
	private questionServ questionserv;
	@Autowired
	private userServ usersev;
	
	@RequestMapping(value = "/getIndexInfoByAdmin")
	@ResponseBody
	public Object getIndexInfoByAdmin(){
		List<Map<String,Object>> list = new ArrayList<>();
		list.add(courseServ.selectAllcourseNum());
		list.add(usersev.selectUserNumByRole("teacher"));
		list.add(usersev.selectUserNumByRole("student"));
		list.add(logrecordserv.getSeeVideoTime());
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("webInfo", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/getOnlineInfoByAdmin")
	@ResponseBody
	public Object getOnlineInfoByAdmin(){
		List<Map<String,Object>> list = logrecordserv.getRecentlyOnlineData(14);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("onlineData", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/getIndexInfoByUser")
	@ResponseBody
	public Object getIndexInfoByUser(
			@RequestParam(value = "userid",required = true)int userid){
		List<Map<String,Object>> list = new ArrayList<>();
		list.add(courseServ.selectAllcourseNum());
		list.add(usersev.selectUserNumByRole("teacher"));
		list.add(usersev.selectUserNumByRole("student"));
		list.add(logrecordserv.getSeeVideoTime(userid));
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("webInfo", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/getOnlineInfoByUser")
	@ResponseBody
	public Object getOnlineInfoByUser(@RequestParam(value = "userid",required=true)int userid){
		List<Map<String,Object>> list = logrecordserv.getRecentlyOnlineData(userid,14);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("onlineData", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/getCoursesByStudyNumRank")
	@ResponseBody
	public Object getCoursesByStudyNumRank(@RequestParam(value = "limit",required=false)Integer limit){
		List<Map<String,Object>> list = courseServ.getCoursesByStudyNumRank(10);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("courseRankList", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/search/courseCategory")
	@ResponseBody
	public Map<String, Object> getCourseCate(){
		Map<String, Object> map = new HashMap<String, Object>();
		categoryCourse cate = new categoryCourse();
		List<categoryCourse> cateList = courseServ.getCourseCate(cate);
		logger.info("查询所有课程分类==============");   
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(cateList));
		map.put("list", clist);
		map.put("code", 20000);
		map.put("message", "访问成功");
		return map;
	}
	@RequestMapping(value = "/select/categoryById")
	@ResponseBody
	public Map<String, Object> getCategoryById(@RequestParam("categoryid")int categoryid){
		Map<String, Object> map = new HashMap<String, Object>();
		categoryCourse cate = new categoryCourse();
		cate.setCategoryid(categoryid);
		List<categoryCourse> cateList = courseServ.getCourseCate(cate);
		logger.info("查询课程分类==============通过ID");   
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(cateList));
		map.put("data", clist);
		map.put("code", 20000);
		map.put("message", "访问成功");
		return map;
	}
	
	@RequestMapping(value = "/upload/image")
	@ResponseBody
	public Object uploadImage(@RequestParam("file") MultipartFile file){
		logger.info("编辑器上传图片====================");   
		ResponseData responseData = ResponseData.ok();
		UploadFile uf = new UploadFile();
		String url = uf.uploadImage(file);
		responseData.putDataValue("url",url);
		return responseData;
	}
	@RequestMapping(value = "/course/addCourse",method=RequestMethod.POST)
	@ResponseBody
	public Object addCourse(@RequestBody courseInfo course){
		logger.info("增加课程!!!===================="+course.getCoursetitle());   
		int i = courseServ.insertCourse(course);
		if(i>0) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "课程创建成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/teacher/auditCourse")
	@ResponseBody
	public Object getCourse(@RequestParam("teacherid")int teacherid){
		logger.info("获得教师的所授课程====================");   
		courseInfo course = new courseInfo();
		course.setTeacherid(teacherid);
		List<courseInfo>courselist =  courseServ.getCourse(course);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(courselist));
		responseData.putDataValue("courselist", clist);
		return responseData;
	}
	/**
	 * 
	 *Title: getCourseByAudit
	 *Description: 获得教师已过审课程
	 *2020年4月3日上午1:19:28
	 * @param teacherid
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/teacher/getCoursePassAudit")
	@ResponseBody
	public Object getCoursePassAudit(@RequestParam("teacherid")int teacherid,@RequestParam("status")boolean status){
		logger.info("获得教师的已通过审核的课程====================");   
		courseInfo course = new courseInfo();
		course.setTeacherid(teacherid);
		course.setStatus(status);
		List<courseInfo>courselist =  courseServ.getCourse(course);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(courselist));
		responseData.putDataValue("courselist", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/teacher/getTeacherPassAuditCourse")
	@ResponseBody
	public Object getTeacherPassAuditCourse(
			@RequestParam("teacherid")int teacherid,
			@RequestParam("status")boolean status,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "title",required = false)String title,
			@RequestParam(value = "isfinish",required = false)String isfinish){
		logger.info("获得教师的已通过审核的课程====================");   
		courseInfo course = new courseInfo();
		course.setTeacherid(teacherid);
		course.setStatus(status);
		if(!"".equals(title)) {
			course.setCoursetitle(title);
		}
		if("false".equals(isfinish)){
			course.setIsfinish(false);
		}
		if("true".equals(isfinish)){
			course.setIsfinish(true);
		}
		Map<String, Object>map =  courseServ.getCourse(course,page,limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("courselist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/getCourseInfo")
	@ResponseBody
	public Object getCourseInfo(@RequestParam("courseid")int courseid){
		logger.info("获得课程信息================ID");   
		courseInfo course = new courseInfo();
		course.setCourseid(courseid);
		List<courseInfo>courselist =  courseServ.getCourse(course);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(courselist));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/allCourse")
	@ResponseBody
	public Object allCourse(){
		logger.info("获得所有课程"); 
		courseInfo course = new courseInfo();
		course.setStatus(true);
		List<courseInfo> list =  courseServ.getCourse(course);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/getCourseByKeyword")
	@ResponseBody
	public Object getCourseByKeyword(@RequestParam(value = "keyword",required = true)String keyword){
		List<courseInfo> list =  courseServ.getCourseByKeyword(keyword);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/getAllCourse")
	@ResponseBody
	public Object getAllCourse(
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "title",required = false)String title,
			@RequestParam(value = "isfinish",required = false)String isfinish,
			@RequestParam(value = "status",required = false)String status){
		logger.info("获得所有课程信息=================分页"); 
		courseInfo course = new courseInfo();
		if(!"".equals(title)) {
			course.setCoursetitle(title);
		}
		if("false".equals(isfinish)){
			course.setIsfinish(false);
		}
		if("true".equals(isfinish)){
			course.setIsfinish(true);
		}
		if("true".equals(status)) {
			course.setStatus(true);
		}
		if("false".equals(status)){
			course.setStatus(false);
		}
		Map<String, Object>map =  courseServ.getCourse(course,page,limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("courselist"), SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/updateCourseInfo")
	@ResponseBody
	public Object updateCourseInfo(@RequestBody courseInfo course){
		logger.info("更新课程信息====================");   
		if(courseServ.updateCourse(course)) {
			ResponseData responseData = ResponseData.ok();
			return responseData;
		}
		ResponseData responseData = ResponseData.serverInternalError();
		return responseData;
	}	
	
	/**
	 * 
	 *Title: addComment
	 *Description:发表课程的评论 
	 *2020年3月30日上午12:46:37
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/course/addComment")
	@ResponseBody
	public Object addComment(@RequestBody commentCourse comment){
		logger.info("增加课程评论!!!====================");   
		if(courseServ.addCourseComment(comment)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "评论发布成功");
			responseData.putDataValue("commentid", comment.getCommentid());
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 
	 *Title: getComment
	 *Description:获得该门课程的所有评论 
	 *2020年3月30日上午12:47:39
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value = "/course/getComment")
	@ResponseBody
	public Object getComment(@RequestParam("courseid")int courseid){
		logger.info("获得课程评论================通过ID");   
		commentCourse comment = new commentCourse();
		comment.setCourseid(courseid);
		List<commentCourse> commentlist =  courseServ.selectCourseComment(comment);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(commentlist,SerializerFeature.DisableCircularReferenceDetect));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 
	 *Title: updateComment
	 *Description: 对已发布评论进行更新，如点赞、修改评论状态
	 *2020年3月30日上午12:48:16
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/course/updateComment")
	@ResponseBody
	public Object updateComment(@RequestBody commentCourse comment){
		logger.info("更新课程评论!!!====================");   
		if(courseServ.updateCourseComment(comment)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "评论更新成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 添加课件
	 *Title: addFile
	 *Description: 
	 *2020年3月30日上午1:39:09
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/course/addFile")
	@ResponseBody
	public Object addFile(@RequestBody fileInfo file){
		logger.info("增加课件!!!====================");   
		if(courseServ.addCourseFile(file)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("fileid", file.getFileid());
			responseData.putDataValue("info", "添加成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * 删除课件
	 *Title: delectFile
	 *Description: 
	 *2020年3月30日上午1:41:50
	 * @param fileid
	 * @return
	 */
	@RequestMapping(value = "/course/delectFile")
	@ResponseBody
	public Object delectFile(@RequestParam("fileid")int fileid){
		logger.info("删除课件!!!====================");   
		if(courseServ.delectCourseFile(fileid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "删除成功");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	/**
	 * 通过教师id=查询所有课件,教师权限
	 *Title: selectFile
	 *Description: 
	 *2020年3月30日上午1:51:17
	 * @param fileid
	 * @return
	 */
	@RequestMapping(value = "/course/selectFile")
	@ResponseBody
	public Object selectFile(
			@RequestParam( value = "userid",required = true)int userid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "courseid",required = false)Integer courseid,
			@RequestParam(value = "fileabout",required = false)String fileabout){
		logger.info("查询课件!!!====================");
		fileInfo file = new fileInfo();
		if(!"".equals(fileabout)) {
			file.setFileabout(fileabout);
		}
		if(courseid!=null) {
		file.setCourseid(courseid);
		}
		file.setUserid(userid);
		Map<String, Object>map = courseServ.selectCourseFile(file, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("filelist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	/**
	 * 通过课程id进行课件查询，学生权限
	 *Title: selectCourseFile
	 *Description: 
	 *2020年3月30日上午2:12:08
	 * @param courseid
	 * @param page
	 * @param limit
	 * @param fileabout
	 * @return
	 */
	@RequestMapping(value = "/course/selectCourseFile")
	@ResponseBody
	public Object selectCourseFile(
			@RequestParam( value = "courseid",required = true)int courseid,
			@RequestParam(value = "page",required = true)int page,
			@RequestParam(value ="limit",required = true)int limit,
			@RequestParam(value = "fileabout",required = false)String fileabout){
		logger.info("查询课件!!!====================");
		fileInfo file = new fileInfo();
		if(!"".equals(fileabout)) {
			file.setFileabout(fileabout);
		}
		if(courseid >0) {
			file.setCourseid(courseid);
		}
		Map<String, Object>map = courseServ.selectCourseFile(file, page, limit);
		ResponseData responseData = ResponseData.ok();
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(map.get("filelist")));
		responseData.putDataValue("total", map.get("total"));
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	@RequestMapping(value = "/course/downloadFile")
	@ResponseBody
	public Object downloadFile(
			@RequestParam( value = "fileid",required = true)int fileid){
		logger.info("下载课件!!!====================");
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("info", "课件下载成功");
		return responseData;
	}
	
	@RequestMapping(value = "/course/getStudyRecord")
	@ResponseBody
	public Object getStudyRecord(
			@RequestParam( value = "userid",required = true)Integer userid,
			@RequestParam( value = "courseid",required = true)Integer courseid){
		logger.info("学生课程记录!!!====================");
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map1 =  logrecordserv.getFileDownloadNum(userid, courseid);
		Map<String,Object> map2 =logrecordserv.getSeeVideoTime(userid, courseid);
		Map<String,Object> map3 =homeworkserv.getHomeworkScore(userid, courseid);
		Map<String,Object> map4 =courseServ.getCommentNum(userid, courseid);
		Map<String,Object> map5 =questionserv.getQestionNum(userid, courseid);
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(list));
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("list", clist);
		return responseData;
	}
	
	
}
