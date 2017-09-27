package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;
	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：会员表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiVip	 extends BaseBean{
	private Integer vipid;//用户id,主键，自增长
	private Integer ustatus;//0：不启用，1：启用
	private Integer aflag;//风险评估:0未完成，1已完成
	private Integer utype;//1公司用户，2个人用户
	private String userpassword;//登录密码（MD5密文）
	private Integer userstatus;//1在线，2离线
	private String usertelephone;//联系电话
	private String uname;//姓名
	private Integer gender;//性别 0男 1女
	private Date createtime;//入库时间
	private Date savetime;//最后一次更新时间
		private String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	public Integer getVipid(){
		return this.vipid;
	}
	public void setVipid(Integer vipid){
		this.vipid=vipid;
	}
	public Integer getUstatus(){
		return this.ustatus;
	}
	public void setUstatus(Integer ustatus){
		this.ustatus=ustatus;
	}
	public Integer getAflag(){
		return this.aflag;
	}
	public void setAflag(Integer aflag){
		this.aflag=aflag;
	}
	public Integer getUtype(){
		return this.utype;
	}
	public void setUtype(Integer utype){
		this.utype=utype;
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
	public Integer getGender(){
		return this.gender;
	}
	public void setGender(Integer gender){
		this.gender=gender;
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