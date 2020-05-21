package com.learn.model;

import java.util.Date;

public class courseInfo {
    private Integer courseid;

    private String coursetitle;

    private String cabout;

    private String picture;

    private Integer studynum;

    private Integer categoryid;

    private Boolean status;

    private Integer courselength;

    private Integer createuserid;

    private Integer teacherid;

    private Date coursecreatetime;

    private Double rating;

    private Boolean isfinish;

    private String value1;

    private String value2;
    
    private User teacher;
    
    private categoryCourse category;
    
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle == null ? null : coursetitle.trim();
    }

    public String getCabout() {
        return cabout;
    }

    public void setCabout(String cabout) {
        this.cabout = cabout == null ? null : cabout.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getStudynum() {
        return studynum;
    }

    public void setStudynum(Integer studynum) {
        this.studynum = studynum;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCourselength() {
        return courselength;
    }

    public void setCourselength(Integer courselength) {
        this.courselength = courselength;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Date getCoursecreatetime() {
        return coursecreatetime;
    }

    public void setCoursecreatetime(Date coursecreatetime) {
        this.coursecreatetime = coursecreatetime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(Boolean isfinish) {
        this.isfinish = isfinish;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2 == null ? null : value2.trim();
    }

	public categoryCourse getCategory() {
		return category;
	}

	public void setCategory(categoryCourse category) {
		this.category = category;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
}