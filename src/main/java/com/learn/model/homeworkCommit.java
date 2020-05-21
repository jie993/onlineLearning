package com.learn.model;

import java.util.Date;

public class homeworkCommit extends homeworkCommitKey {
    private String hwcommitfileuri;

    private Date hwcommitdate;

    private Integer hwcscore;

    private Boolean ischeck;

    private String remark;

    private String value1;

    private String value2;
    
    private User user;

    public String getHwcommitfileuri() {
        return hwcommitfileuri;
    }

    public void setHwcommitfileuri(String hwcommitfileuri) {
        this.hwcommitfileuri = hwcommitfileuri == null ? null : hwcommitfileuri.trim();
    }

    public Date getHwcommitdate() {
        return hwcommitdate;
    }

    public void setHwcommitdate(Date hwcommitdate) {
        this.hwcommitdate = hwcommitdate;
    }

    public Integer getHwcscore() {
        return hwcscore;
    }

    public void setHwcscore(Integer hwcscore) {
        this.hwcscore = hwcscore;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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