package com.learn.model;

public class classInfo {
    private Integer classid;

    private String classtitle;

    private String classabout;

    private String medianame;

    private String mediauri;

    private Integer medialength;

    private Integer learnednum;

    private Integer userid;

    private Integer courseid;

    private Integer sectionid;

    private String value1;

    private String value2;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClasstitle() {
        return classtitle;
    }

    public void setClasstitle(String classtitle) {
        this.classtitle = classtitle == null ? null : classtitle.trim();
    }

    public String getClassabout() {
        return classabout;
    }

    public void setClassabout(String classabout) {
        this.classabout = classabout == null ? null : classabout.trim();
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame == null ? null : medianame.trim();
    }

    public String getMediauri() {
        return mediauri;
    }

    public void setMediauri(String mediauri) {
        this.mediauri = mediauri == null ? null : mediauri.trim();
    }

    public Integer getMedialength() {
        return medialength;
    }

    public void setMedialength(Integer medialength) {
        this.medialength = medialength;
    }

    public Integer getLearnednum() {
        return learnednum;
    }

    public void setLearnednum(Integer learnednum) {
        this.learnednum = learnednum;
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

    public Integer getSectionid() {
        return sectionid;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
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