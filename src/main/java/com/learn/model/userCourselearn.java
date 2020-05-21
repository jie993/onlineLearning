package com.learn.model;

public class userCourselearn extends userCourselearnKey {
    private Integer urating;

    private Integer coursescore;

    private Integer learntime;

    private Integer homescore;

    private Integer commentnum;

    private Integer ansquestionnum;

    private Integer downloadfilenum;

    private String value1;

    private String value2;

    private User user;
    
    public Integer getUrating() {
        return urating;
    }

    public void setUrating(Integer urating) {
        this.urating = urating;
    }

    public Integer getCoursescore() {
        return coursescore;
    }

    public void setCoursescore(Integer coursescore) {
        this.coursescore = coursescore;
    }

    public Integer getLearntime() {
        return learntime;
    }

    public void setLearntime(Integer learntime) {
        this.learntime = learntime;
    }

    public Integer getHomescore() {
        return homescore;
    }

    public void setHomescore(Integer homescore) {
        this.homescore = homescore;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public Integer getAnsquestionnum() {
        return ansquestionnum;
    }

    public void setAnsquestionnum(Integer ansquestionnum) {
        this.ansquestionnum = ansquestionnum;
    }

    public Integer getDownloadfilenum() {
        return downloadfilenum;
    }

    public void setDownloadfilenum(Integer downloadfilenum) {
        this.downloadfilenum = downloadfilenum;
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