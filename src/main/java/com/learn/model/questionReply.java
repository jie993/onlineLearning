package com.learn.model;

import java.util.Date;

public class questionReply {
    private Integer questionid;

    private String questiontitle;

    private String qcontent;

    private Date qcreatetime;

    private Integer courseid;

    private Integer userid;

    private Boolean qisfinish;

    private Integer teacherid;

    private String replycontent;

    private Integer qbnum;

    private String value1;

    private String value2;
    
    private User user;
    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle == null ? null : questiontitle.trim();
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent == null ? null : qcontent.trim();
    }

    public Date getQcreatetime() {
        return qcreatetime;
    }

    public void setQcreatetime(Date qcreatetime) {
        this.qcreatetime = qcreatetime;
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

    public Boolean getQisfinish() {
        return qisfinish;
    }

    public void setQisfinish(Boolean qisfinish) {
        this.qisfinish = qisfinish;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent == null ? null : replycontent.trim();
    }

    public Integer getQbnum() {
        return qbnum;
    }

    public void setQbnum(Integer qbnum) {
        this.qbnum = qbnum;
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