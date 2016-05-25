package com.nealma.framework.model;

import java.util.Date;

public class Log extends BaseModel {

    private Long id;
    private String description;
    private String method;
    private String type;
    private String ip;
    private String exceptionCode;
    private String exceptionDetail;
    private String params;
    private String username;
    private Date createDate;


    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Log [createDate=" + createDate + ", description=" + description
                + ", exceptionCode=" + exceptionCode + ", exceptionDetail="
                + exceptionDetail + ", id=" + id + ", ip=" + ip + ", method="
                + method + ", params=" + params + ", type=" + type
                + ", username=" + username + "]";
    }

}
