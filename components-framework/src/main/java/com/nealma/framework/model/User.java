package com.nealma.framework.model;

import java.util.Date;
import java.util.List;

/**
 * Created by neal.ma on 5/17/16.
 */
public class User extends BaseModel{
    private Long id;
    private String username;
    private String password;
    private String salt;
    private Date createTime;
    private Date lastEditTime;
    private Integer rowStatus;
    private List<Role> roles;
    private List<Resource> resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getRowStatus() {
        return rowStatus;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setRowStatus(Integer rowStatus) {


        this.rowStatus = rowStatus;
    }

    public String getCredentialSalt(){
        return username + salt;
    }
}

