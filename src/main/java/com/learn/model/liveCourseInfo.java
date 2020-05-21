package com.learn.model;

import java.util.Date;

public class liveCourseInfo {
    private Integer id;

    private String liveclassname;

    private Date starttime;

    private Date finshtime;

    private String liveurl;

    private Integer courseid;

    private Integer userid;

    private String value1;

    private String value2;
    
    private User user;
    
    private courseInfo course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiveclassname() {
        return liveclassname;
    }

    public void setLiveclassname(String liveclassname) {
        this.liveclassname = liveclassname == null ? null : liveclassname.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getFinshtime() {
        return finshtime;
    }

    public void setFinshtime(Date finshtime) {
        this.finshtime = finshtime;
    }

    public String getLiveurl() {
        return liveurl;
    }

    public void setLiveurl(String liveurl) {
        this.liveurl = liveurl == null ? null : liveurl.trim();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public courseInfo getCourse() {
		return course;
	}

	public void setCourse(courseInfo course) {
		this.course = course;
	}
}