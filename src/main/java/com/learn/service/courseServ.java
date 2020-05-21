package com.learn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.categoryCourseMapper;
import com.learn.dao.commentCourseMapper;
import com.learn.dao.courseInfoMapper;
import com.learn.dao.fileInfoMapper;

import com.learn.model.carousel;
import com.learn.model.categoryCourse;
import com.learn.model.commentCourse;
import com.learn.model.courseInfo;
import com.learn.model.courseIntro;
import com.learn.model.fileInfo;

import com.learn.model.sectionCourseInfo;

@Service
public class courseServ {
	@Autowired
	private courseInfoMapper coursedao;
	@Autowired
	private categoryCourseMapper courseCatedao;
	@Autowired
	private commentCourseMapper commentdao;
	@Autowired
	private fileInfoMapper filedao;
	
	/**
	 * 通过课程id批量查询课程信息
	 *Title: getCourseByCourseidList
	 *Description: 
	 *2020年4月1日上午11:53:10
	 * @param courseidList
	 * @return
	 */
	public List<courseInfo> getCourseByCourseidList(List<Integer> courseidList,String coursetitle) {
		Map<String,Object> map = new HashMap<>();
		map.put("courseidList", courseidList);
		map.put("coursetitle", coursetitle);
		List<courseInfo> courses = coursedao.selectByList(map);
		return courses;
	}
	
	// 获取课程的信息
	public List<courseInfo> getCourse(courseInfo course) {

		List<courseInfo> courses = coursedao.queryByPage(course);

		return courses;
	}
	
	/**
	 * 
	 *Title: getCourseidByTeacher
	 *Description: 获取课程id通过teacherid
	 *2020年4月4日上午6:55:29
	 * @param course
	 * @return
	 */
	public List<Integer> getCourseidByTeacherid(Integer teacherid){
		courseInfo course = new courseInfo();
		course.setTeacherid(teacherid);
		List<courseInfo> courses = coursedao.queryByPage2(course);
		List<Integer> courseidList = new ArrayList<Integer>();
		for(courseInfo item:courses) {
			courseidList.add(item.getCourseid());
		}
		return courseidList;
	}
	
	// 获取课程的信息==========分页查询
	public Map<String, Object> getCourse(courseInfo course, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<courseInfo> clist = coursedao.queryByPage(course);
		PageInfo<courseInfo> pageinfo = new PageInfo<>(clist);
		long total = pageinfo.getTotal();
		map.put("courselist", clist);
		map.put("total", total);
		return map;
	}

	public List<courseInfo> getCourseByKeyword(String keyword) {
		List<courseInfo> clist = coursedao.selectHotCourseByKeyword(keyword);
		return clist;
	}
	
	public Map<String,Object> selectAllcourseNum() {
		Map<String,Object> num = coursedao.selectCourseNum();
		return num;
	}
	
	// 获得课程的分类
	public List<categoryCourse> getCourseCate(categoryCourse cate) {
		List<categoryCourse> catelist = courseCatedao.queryByPage(cate);
		System.out.println(catelist.toString());
		return catelist;
	}

	// 添加一门课程
	public int insertCourse(courseInfo course) {
		int i = coursedao.insertSelective(course);
		return i;
	}

	// 更新课程基本信息
	public boolean updateCourse(courseInfo course) {
		int i = coursedao.updateByPrimaryKeySelective(course);
		if (i > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 增加课程评论 
	 * Title: addCourseComment 
	 * Description: 2020年3月30日上午1:08:57
	 * 
	 * @param comment
	 * @return
	 */
	public boolean addCourseComment(commentCourse comment) {
		int i = commentdao.insertSelectiveReturnid(comment);
		if (i > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询课程评论 
	 * Title: selectCourseComment 
	 * Description: 2020年3月30日上午1:09:04
	 * 
	 * @param comment
	 * @return
	 */
	public List<commentCourse> selectCourseComment(commentCourse comment) {
		List<commentCourse> slist = commentdao.queryByPage(comment);
		return slist;
	}

	/**
	 * 更新课程评论 
	 * Title: updateCourseComment
	 *  Description: 
	 *  2020年3月30日上午1:09:14
	 * 
	 * @param comment
	 * @return
	 */
	public boolean updateCourseComment(commentCourse comment) {
		int i = commentdao.updateByPrimaryKeySelective(comment);
		if (i > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 给课程添加课件
	 *Title: addCourseFile
	 *Description: 
	 *2020年3月30日上午1:36:06
	 * @param file
	 * @return
	 */
	public boolean addCourseFile(fileInfo file) {
		int i = filedao.insertSelective(file);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除课件
	 *Title: delectCourseFile
	 *Description: 
	 *2020年3月30日上午1:35:58
	 * @param fileid
	 * @return
	 */
	public boolean delectCourseFile(Integer fileid) {
		int i = filedao.deleteByPrimaryKey(fileid);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询课件，进行分页
	 *Title: selectCourseFile
	 *Description: 
	 *2020年3月30日上午1:35:50
	 * @param file
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String, Object> selectCourseFile(fileInfo file, int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<fileInfo> flist = filedao.queryByPage(file);
		PageInfo<fileInfo> pageinfo = new PageInfo<>(flist);
		long total = pageinfo.getTotal();
		map.put("filelist", flist);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 课件更新操作
	 *Title: updateCourseFile
	 *Description: 
	 *2020年3月30日上午1:35:40
	 * @param file
	 * @return
	 */
	public boolean updateCourseFile(fileInfo file) {
		int i = filedao.updateByPrimaryKeySelective(file);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> getCommentNum(Integer userid,Integer courseid){
		commentCourse comment = new commentCourse();
		comment.setCourseid(courseid);
		comment.setUserid(userid);
		Map<String,Object> map = commentdao.selectCommentNum(comment);
		return map;
	}
	
	public List<Map<String,Object>> getCoursesByStudyNumRank(Integer limit){
		List<Map<String,Object>> courses = coursedao.selectStudyNumRank(limit);
		return courses;
	}
	
	public boolean updateAddStudyNum(Integer courseid) {
		int i = coursedao.updateAddStudyNum(courseid);
		if(i>0) {
			return true;
		}
		return false;
	}
}
