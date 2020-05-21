package com.learn.model;

import java.util.Date;

public class logLoginandout {
    private Integer id;

    private Integer userid;

    private Date logindate;

    private Date logoutdate;

    private String clientip;

    private String value1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public Date getLogoutdate() {
        return logoutdate;
    }

    public void setLogoutdate(Date logoutdate) {
        this.logoutdate = logoutdate;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }
}