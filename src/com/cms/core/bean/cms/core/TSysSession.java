package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：会话表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TSysSession	 extends BaseBean {
	private Long sessionid;//主键，自增长
	private String sessiontoken;//    SESSIONTOKEN
	private Date lasttime;//最后一次更新时间
	private Integer status;//会话状态
	private Integer userid;//用户id
	public Long getSessionid(){
		return this.sessionid;
	}
	public void setSessionid(Long sessionid){
		this.sessionid=sessionid;
	}
	public String getSessiontoken(){
		return this.sessiontoken;
	}
	public void setSessiontoken(String sessiontoken){
		this.sessiontoken=sessiontoken;
	}
	public Date getLasttime(){
		return this.lasttime;
	}
	public void setLasttime(Date lasttime){
		this.lasttime=lasttime;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getUserid(){
		return this.userid;
	}
	public void setUserid(Integer userid){
		this.userid=userid;
	}

}