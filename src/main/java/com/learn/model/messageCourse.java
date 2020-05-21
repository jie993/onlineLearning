package com.learn.model;

import java.util.Date;

public class messageCourse {
    private Integer messageid;

    private String messagetitle;

    private String messagecontent;

    private Integer mbrowsenum;

    private Date mcreatetime;

    private Integer courseid;

    private Integer userid;

    private String value1;

    private String value2;

    private courseInfo course;
    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getMessagetitle() {
        return messagetitle;
    }

    public void setMessagetitle(String messagetitle) {
        this.messagetitle = messagetitle == null ? null : messagetitle.trim();
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent == null ? null : messagecontent.trim();
    }

    public Integer getMbrowsenum() {
        return mbrowsenum;
    }

    public void setMbrowsenum(Integer mbrowsenum) {
        this.mbrowsenum = mbrowsenum;
    }

    public Date getMcreatetime() {
        return mcreatetime;
    }

    public void setMcreatetime(Date mcreatetime) {
        this.mcreatetime = mcreatetime;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

	public courseInfo getCourse() {
		return course;
	}

	public void setCourse(courseInfo course) {
		this.course = course;
	}
}