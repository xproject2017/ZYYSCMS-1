package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;

	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：用户表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TSysStaff	 extends BaseBean {
	private Integer userid;//用户id,主键，自增长
	private Integer roleid;//角色id
	private Integer ustatus;//0：不启用，1：启用
	private String permissionflag;//权限 默认值‘111’ 修改|新增|删除



		private String username;//登录名
	private String userpassword;//登录密码（MD5密文）
	private Integer userstatus;//1在线，2离线
	private String usertelephone;//联系电话
	private String uname;//员工姓名
	private Integer cuserid;//创建人ID
	private Date createtime;//入库时间
	private Date savetime;//最后一次更新时间
	public Integer getUserid(){
		return this.userid;
	}
	public void setUserid(Integer userid){
		this.userid=userid;
	}
	public Integer getRoleid(){
		return this.roleid;
	}
	public void setRoleid(Integer roleid){
		this.roleid=roleid;
	}
	public Integer getUstatus(){
		return this.ustatus;
	}
	public void setUstatus(Integer ustatus){
		this.ustatus=ustatus;
	}
	public String getPermissionflag(){
		return this.permissionflag;
	}
	public void setPermissionflag(String permissionflag){
		this.permissionflag=permissionflag;
	}
	public String getUserpassword(){
		return this.userpassword;
	}
	public void setUserpassword(String userpassword){
		this.userpassword=userpassword;
	}
	public Integer getUserstatus(){
		return this.userstatus;
	}
	public void setUserstatus(Integer userstatus){
		this.userstatus=userstatus;
	}
	public String getUsertelephone(){
		return this.usertelephone;
	}
	public void setUsertelephone(String usertelephone){
		this.usertelephone=usertelephone;
	}
	public String getUname(){
		return this.uname;
	}
	public void setUname(String uname){
		this.uname=uname;
	}
	public Integer getCuserid(){
		return this.cuserid;
	}
	public void setCuserid(Integer cuserid){
		this.cuserid=cuserid;
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
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

}