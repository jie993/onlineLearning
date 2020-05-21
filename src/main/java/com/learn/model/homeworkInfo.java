package com.learn.model;

import java.util.Date;

public class homeworkInfo {
    private Integer wid;

    private String wtitle;

    private Date wcreatedate;

    private Date wfinishdate;

    private Integer hits;

    private Integer wcommitnum;

    private String referfile;

    private Integer courseid;

    private Integer userid;

    private String value1;

    private String value2;

    private String wcontent;

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWtitle() {
        return wtitle;
    }

    public void setWtitle(String wtitle) {
        this.wtitle = wtitle == null ? null : wtitle.trim();
    }

    public Date getWcreatedate() {
        return wcreatedate;
    }

    public void setWcreatedate(Date wcreatedate) {
        this.wcreatedate = wcreatedate;
    }

    public Date getWfinishdate() {
        return wfinishdate;
    }

    public void setWfinishdate(Date wfinishdate) {
        this.wfinishdate = wfinishdate;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getWcommitnum() {
        return wcommitnum;
    }

    public void setWcommitnum(Integer wcommitnum) {
        this.wcommitnum = wcommitnum;
    }

    public String getReferfile() {
        return referfile;
    }

    public void setReferfile(String referfile) {
        this.referfile = referfile == null ? null : referfile.trim();
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

    public String getWcontent() {
        return wcontent;
    }

    public void setWcontent(String wcontent) {
        this.wcontent = wcontent == null ? null : wcontent.trim();
    }
}