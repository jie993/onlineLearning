package com.learn.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class auditCourse {
    private Integer auditid;

    private String atitle;

    private String acontent;

    private String afileuri;

    private Date acreatetime;
    
    @JSONField(serialzeFeatures= {SerializerFeature.WriteNullBooleanAsFalse})
    private Boolean ispass;

    private Integer userid;

    private Integer courseid;

    private Integer auditorid;

    private String aAdvice;

    private Date aReplytime;

    private String value1;

    private String value2;
    
    private User user;
    
    private courseInfo course;
    
    
    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle == null ? null : atitle.trim();
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent == null ? null : acontent.trim();
    }

    public String getAfileuri() {
        return afileuri;
    }

    public void setAfileuri(String afileuri) {
        this.afileuri = afileuri == null ? null : afileuri.trim();
    }

    public Date getAcreatetime() {
        return acreatetime;
    }

    public void setAcreatetime(Date acreatetime) {
        this.acreatetime = acreatetime;
    }

    public Boolean getIspass() {
        return ispass;
    }

    public void setIspass(Boolean ispass) {
        this.ispass = ispass;
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

    public Integer getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(Integer auditorid) {
        this.auditorid = auditorid;
    }
    @JSONField(name="aAdvice")
    public String getAAdvice() {
        return aAdvice;
    }
    @JSONField(name="aAdvice")
    public void setaAdvice(String aAdvice) {
        this.aAdvice = aAdvice == null ? null : aAdvice.trim();
    }
    @JSONField(name="aReplytime")
    public Date getAReplytime() {
        return aReplytime;
    }
    @JSONField(name="aReplytime")
    public void setaReplytime(Date aReplytime) {
        this.aReplytime = aReplytime;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}