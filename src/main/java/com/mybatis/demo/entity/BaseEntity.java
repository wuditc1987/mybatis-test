package com.mybatis.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wudi
 * @version 1.0
 * @Description 实体基类
 * @date 2019/6/19 1:35 PM
 */
public class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private Integer id;

    private int createUserId;

    private int updateUserId;

    private Date createTime;

    private Date updateTime;

    /**
     * 用于乐观锁的实现
     */
    private int versionNo;

    private String searchText;

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getId(){
        return id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
