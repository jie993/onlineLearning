package com.learn.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class categoryCourse {
    private Integer categoryid;

    private String catename;

    private Integer cateparentid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date catecreatetime;

    private String value1;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }

    public Integer getCateparentid() {
        return cateparentid;
    }

    public void setCateparentid(Integer cateparentid) {
        this.cateparentid = cateparentid;
    }

    public Date getCatecreatetime() {
        return catecreatetime;
    }

    public void setCatecreatetime(Date catecreatetime) {
        this.catecreatetime = catecreatetime;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }
}