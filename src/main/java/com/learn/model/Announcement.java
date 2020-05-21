package com.learn.model;

import java.util.Date;

public class Announcement {
    private Integer annouid;

    private String annoutitle;

    private String annoucontent;

    private String afile;

    private Integer abrowsenum;

    private Boolean alimit;

    private Date acreatetime;

    private Integer userid;

    private String value1;

    private String value2;

    public Integer getAnnouid() {
        return annouid;
    }

    public void setAnnouid(Integer annouid) {
        this.annouid = annouid;
    }

    public String getAnnoutitle() {
        return annoutitle;
    }

    public void setAnnoutitle(String annoutitle) {
        this.annoutitle = annoutitle == null ? null : annoutitle.trim();
    }

    public String getAnnoucontent() {
        return annoucontent;
    }

    public void setAnnoucontent(String annoucontent) {
        this.annoucontent = annoucontent == null ? null : annoucontent.trim();
    }

    public String getAfile() {
        return afile;
    }

    public void setAfile(String afile) {
        this.afile = afile == null ? null : afile.trim();
    }

    public Integer getAbrowsenum() {
        return abrowsenum;
    }

    public void setAbrowsenum(Integer abrowsenum) {
        this.abrowsenum = abrowsenum;
    }

    public Boolean getAlimit() {
        return alimit;
    }

    public void setAlimit(Boolean alimit) {
        this.alimit = alimit;
    }

    public Date getAcreatetime() {
        return acreatetime;
    }

    public void setAcreatetime(Date acreatetime) {
        this.acreatetime = acreatetime;
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
}