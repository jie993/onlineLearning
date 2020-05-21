package com.learn.model;

import java.util.Date;

public class courseIntro {
    private Integer courseIntroid;

    private String cintroVideourl;

    private String cintroNotice;

    private Date operationdate;

    private Integer courseid;

    private Integer userid;

    private String value;

    public Integer getCourseIntroid() {
        return courseIntroid;
    }

    public void setCourseIntroid(Integer courseIntroid) {
        this.courseIntroid = courseIntroid;
    }

    public String getCintroVideourl() {
        return cintroVideourl;
    }

    public void setCintroVideourl(String cintroVideourl) {
        this.cintroVideourl = cintroVideourl == null ? null : cintroVideourl.trim();
    }

    public String getCintroNotice() {
        return cintroNotice;
    }

    public void setCintroNotice(String cintroNotice) {
        this.cintroNotice = cintroNotice == null ? null : cintroNotice.trim();
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}