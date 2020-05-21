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
 * @author ���ν�
 * 2020��3��30������12:45:09
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
		logger.info("��ѯ���пγ̷���==============");   
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(cateList));
		map.put("list", clist);
		map.put("code", 20000);
		map.put("message", "���ʳɹ�");
		return map;
	}
	@RequestMapping(value = "/select/categoryById")
	@ResponseBody
	public Map<String, Object> getCategoryById(@RequestParam("categoryid")int categoryid){
		Map<String, Object> map = new HashMap<String, Object>();
		categoryCourse cate = new categoryCourse();
		cate.setCategoryid(categoryid);
		List<categoryCourse> cateList = courseServ.getCourseCate(cate);
		logger.info("��ѯ�γ̷���==============ͨ��ID");   
		JSONArray clist = JSONArray.fromObject(JSON.toJSONString(cateList));
		map.put("data", clist);
		map.put("code", 20000);
		map.put("message", "���ʳɹ�");
		return map;
	}
	
	@RequestMapping(value = "/upload/image")
	@ResponseBody
	public Object uploadImage(@RequestParam("file") MultipartFile file){
		logger.info("�༭���ϴ�ͼƬ====================");   
		ResponseData responseData = ResponseData.ok();
		UploadFile uf = new UploadFile();
		String url = uf.uploadImage(file);
		responseData.putDataValue("url",url);
		return responseData;
	}
	@RequestMapping(value = "/course/addCourse",method=RequestMethod.POST)
	@ResponseBody
	public Object addCourse(@RequestBody courseInfo course){
		logger.info("���ӿγ�!!!===================="+course.getCoursetitle());   
		int i = courseServ.insertCourse(course);
		if(i>0) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "�γ̴����ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	@RequestMapping(value = "/teacher/auditCourse")
	@ResponseBody
	public Object getCourse(@RequestParam("teacherid")int teacherid){
		logger.info("��ý�ʦ�����ڿγ�====================");   
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
	 *Description: ��ý�ʦ�ѹ���γ�
	 *2020��4��3������1:19:28
	 * @param teacherid
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/teacher/getCoursePassAudit")
	@ResponseBody
	public Object getCoursePassAudit(@RequestParam("teacherid")int teacherid,@RequestParam("status")boolean status){
		logger.info("��ý�ʦ����ͨ����˵Ŀγ�====================");   
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
		logger.info("��ý�ʦ����ͨ����˵Ŀγ�====================");   
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
		logger.info("��ÿγ���Ϣ================ID");   
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
		logger.info("������пγ�"); 
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
		logger.info("������пγ���Ϣ=================��ҳ"); 
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
		logger.info("���¿γ���Ϣ====================");   
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
	 *Description:����γ̵����� 
	 *2020��3��30������12:46:37
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/course/addComment")
	@ResponseBody
	public Object addComment(@RequestBody commentCourse comment){
		logger.info("���ӿγ�����!!!====================");   
		if(courseServ.addCourseComment(comment)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "���۷����ɹ�");
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
	 *Description:��ø��ſγ̵��������� 
	 *2020��3��30������12:47:39
	 * @param courseid
	 * @return
	 */
	@RequestMapping(value = "/course/getComment")
	@ResponseBody
	public Object getComment(@RequestParam("courseid")int courseid){
		logger.info("��ÿγ�����================ͨ��ID");   
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
	 *Description: ���ѷ������۽��и��£�����ޡ��޸�����״̬
	 *2020��3��30������12:48:16
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/course/updateComment")
	@ResponseBody
	public Object updateComment(@RequestBody commentCourse comment){
		logger.info("���¿γ�����!!!====================");   
		if(courseServ.updateCourseComment(comment)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "���۸��³ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * ��ӿμ�
	 *Title: addFile
	 *Description: 
	 *2020��3��30������1:39:09
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/course/addFile")
	@ResponseBody
	public Object addFile(@RequestBody fileInfo file){
		logger.info("���ӿμ�!!!====================");   
		if(courseServ.addCourseFile(file)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("fileid", file.getFileid());
			responseData.putDataValue("info", "��ӳɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	/**
	 * ɾ���μ�
	 *Title: delectFile
	 *Description: 
	 *2020��3��30������1:41:50
	 * @param fileid
	 * @return
	 */
	@RequestMapping(value = "/course/delectFile")
	@ResponseBody
	public Object delectFile(@RequestParam("fileid")int fileid){
		logger.info("ɾ���μ�!!!====================");   
		if(courseServ.delectCourseFile(fileid)) {
			ResponseData responseData = ResponseData.ok();
			responseData.putDataValue("info", "ɾ���ɹ�");
			return responseData;
		}else {
			ResponseData responseData = ResponseData.serverInternalError();
			return responseData;
		}
	}
	
	/**
	 * ͨ����ʦid=��ѯ���пμ�,��ʦȨ��
	 *Title: selectFile
	 *Description: 
	 *2020��3��30������1:51:17
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
		logger.info("��ѯ�μ�!!!====================");
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
	 * ͨ���γ�id���пμ���ѯ��ѧ��Ȩ��
	 *Title: selectCourseFile
	 *Description: 
	 *2020��3��30������2:12:08
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
		logger.info("��ѯ�μ�!!!====================");
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
		logger.info("���ؿμ�!!!====================");
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("info", "�μ����سɹ�");
		return responseData;
	}
	
	@RequestMapping(value = "/course/getStudyRecord")
	@ResponseBody
	public Object getStudyRecord(
			@RequestParam( value = "userid",required = true)Integer userid,
			@RequestParam( value = "courseid",required = true)Integer courseid){
		logger.info("ѧ���γ̼�¼!!!====================");
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
