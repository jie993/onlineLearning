package com.learn.model;

import java.util.Date;

public class fileInfo {
    private Integer fileid;

    private String fileuri;

    private Integer filesize;

    private String fileabout;

    private Integer fdownloadnum;

    private Date filecreatetime;

    private Integer userid;

    private Integer courseid;

    private String value1;

    private String value2;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFileuri() {
        return fileuri;
    }

    public void setFileuri(String fileuri) {
        this.fileuri = fileuri == null ? null : fileuri.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getFileabout() {
        return fileabout;
    }

    public void setFileabout(String fileabout) {
        this.fileabout = fileabout == null ? null : fileabout.trim();
    }

    public Integer getFdownloadnum() {
        return fdownloadnum;
    }

    public void setFdownloadnum(Integer fdownloadnum) {
        this.fdownloadnum = fdownloadnum;
    }

    public Date getFilecreatetime() {
        return filecreatetime;
    }

    public void setFilecreatetime(Date filecreatetime) {
        this.filecreatetime = filecreatetime;
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
}