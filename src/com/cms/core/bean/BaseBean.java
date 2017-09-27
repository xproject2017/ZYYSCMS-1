package com.cms.core.bean;

import com.cms.core.bean.cms.core.TSysStaff;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SeleneFox
 * Date: 13-4-20
 * Time: 下午12:31
 * 需要用于页面交互的ModeBean
 */

public class BaseBean implements Serializable
{
    private Integer result;//结果字段，0：成功；1：失败
    private String failinfo;//当result为1时，存储错误信息
    private String act;

    private Integer currentPage; //当前页码
    private Integer currentSize; //   currentSize=currentPage* pageSize      // LIMIT #{currentSize}, #{pageSize}
    private Integer totalPage;//总页数
    private Integer totalNum;//总记录数
    private Integer pageSize;//页数记录数

    private String token;//令牌
    private String from;
    private String tablename;//表名称
    private String databasename;//数据库名称
    private Integer dbcnt;//返回dml更新的行数

    private String sTime;//开始时间 根据实际情况来确定时间格式  建议YYYY-MM-DD hh24:mi:ss
    private String eTime;//结束时间
    private TSysStaff user;//用户姓名
    private Integer pageFlag;//是否分页标识，0不分页，1分页

    private Date sTimeDB;//开始时间 转成日期型，提供给Dao层使用
    private Date eTimeDB;//结束时间
    private Integer readonly;//是否只读 0：否 1：是

    private Integer prev_pageflag;//detail页面是否需要出现上一条和下一条 0：否 1：是
    private Integer prev_id; //上一条id
    private String prev_title;//上一条标题
    private Integer next_id;//下一条id
    private String next_title;//下一条标题
    private Integer ngid1;//一级导航id
    private Integer ngid2;//二级导航id
    private String ngname1;//一级导航名 业务层统一填充
    private String ngname2;//二级导航名 业务层统一填充

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getFailinfo() {
        return failinfo;
    }

    public void setFailinfo(String failinfo) {
        this.failinfo = failinfo;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public Integer getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(Integer pageFlag) {
        this.pageFlag = pageFlag;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public Integer getDbcnt() {
        return dbcnt;
    }

    public void setDbcnt(Integer dbcnt) {
        this.dbcnt = dbcnt;
    }

    public Date getsTimeDB() {
        return sTimeDB;
    }

    public void setsTimeDB(Date sTimeDB) {
        this.sTimeDB = sTimeDB;
    }

    public Date geteTimeDB() {
        return eTimeDB;
    }

    public void seteTimeDB(Date eTimeDB) {
        this.eTimeDB = eTimeDB;
    }

    public TSysStaff getUser() {
        return user;
    }

    public void setUser(TSysStaff user) {
        this.user = user;
    }

    public Integer getReadonly() {
        return readonly;
    }

    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    public Integer getPrev_pageflag() {
        return prev_pageflag;
    }

    public void setPrev_pageflag(Integer prev_pageflag) {
        this.prev_pageflag = prev_pageflag;
    }

    public Integer getPrev_id() {
        return prev_id;
    }

    public void setPrev_id(Integer prev_id) {
        this.prev_id = prev_id;
    }

    public String getPrev_title() {
        return prev_title;
    }

    public void setPrev_title(String prev_title) {
        this.prev_title = prev_title;
    }

    public Integer getNext_id() {
        return next_id;
    }

    public void setNext_id(Integer next_id) {
        this.next_id = next_id;
    }

    public String getNext_title() {
        return next_title;
    }

    public void setNext_title(String next_title) {
        this.next_title = next_title;
    }

    public Integer getNgid1() {
        return ngid1;
    }

    public void setNgid1(Integer ngid1) {
        this.ngid1 = ngid1;
    }

    public Integer getNgid2() {
        return ngid2;
    }

    public void setNgid2(Integer ngid2) {
        this.ngid2 = ngid2;
    }

    public String getNgname1() {
        return ngname1;
    }

    public void setNgname1(String ngname1) {
        this.ngname1 = ngname1;
    }

    public String getNgname2() {
        return ngname2;
    }

    public void setNgname2(String ngname2) {
        this.ngname2 = ngname2;
    }
}
