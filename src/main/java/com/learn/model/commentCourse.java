package com.learn.model;

import java.util.Date;

public class commentCourse {
    private Integer commentid;

    private String comcontent;

    private Boolean comisdisplay;

    private Date comcreatetime;

    private Integer comlikenum;

    private Integer userid;

    private Integer courseid;

    private String value1;

    private String value2;
    
    private User user;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getComcontent() {
        return comcontent;
    }

    public void setComcontent(String comcontent) {
        this.comcontent = comcontent == null ? null : comcontent.trim();
    }

    public Boolean getComisdisplay() {
        return comisdisplay;
    }

    public void setComisdisplay(Boolean comisdisplay) {
        this.comisdisplay = comisdisplay;
    }

    public Date getComcreatetime() {
        return comcreatetime;
    }

    public void setComcreatetime(Date comcreatetime) {
        this.comcreatetime = comcreatetime;
    }

    public Integer getComlikenum() {
        return comlikenum;
    }

    public void setComlikenum(Integer comlikenum) {
        this.comlikenum = comlikenum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
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
}