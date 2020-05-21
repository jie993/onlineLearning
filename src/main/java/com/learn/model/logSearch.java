package com.learn.model;

import java.util.Date;

public class logSearch {
    private Integer id;

    private Integer userid;

    private String searchkey;

    private Date searchdate;

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

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey == null ? null : searchkey.trim();
    }

    public Date getSearchdate() {
        return searchdate;
    }

    public void setSearchdate(Date searchdate) {
        this.searchdate = searchdate;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }
}