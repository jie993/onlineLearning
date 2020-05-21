package com.learn.model;

public class courseKnowledgepoint {
    private Integer kpintoid;

    private String kpointName;

    private String kpointContent;

    private Integer kpointWeight;

    private Integer courseid;

    private String value;

    public Integer getKpintoid() {
        return kpintoid;
    }

    public void setKpintoid(Integer kpintoid) {
        this.kpintoid = kpintoid;
    }

    public String getKpointName() {
        return kpointName;
    }

    public void setKpointName(String kpointName) {
        this.kpointName = kpointName == null ? null : kpointName.trim();
    }

    public String getKpointContent() {
        return kpointContent;
    }

    public void setKpointContent(String kpointContent) {
        this.kpointContent = kpointContent == null ? null : kpointContent.trim();
    }

    public Integer getKpointWeight() {
        return kpointWeight;
    }

    public void setKpointWeight(Integer kpointWeight) {
        this.kpointWeight = kpointWeight;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}