package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：角色表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TSysRole	 extends BaseBean {
	private Integer roleid;//角色id,主键，自增长
	private String rolename;//角色名
	private Date createtime;//入库时间
	private Date savetime;//最后一次更新时间
	public Integer getRoleid(){
		return this.roleid;
	}
	public void setRoleid(Integer roleid){
		this.roleid=roleid;
	}
	public String getRolename(){
		return this.rolename;
	}
	public void setRolename(String rolename){
		this.rolename=rolename;
	}
	public Date getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Date createtime){
		this.createtime=createtime;
	}
	public Date getSavetime(){
		return this.savetime;
	}
	public void setSavetime(Date savetime){
		this.savetime=savetime;
	}

}