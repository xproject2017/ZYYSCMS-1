package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：产品咨询表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiPconsultation	 extends BaseBean {
	private Integer consid;//主键，自增长
	private Integer navigationid1;//一级导航id
	private Integer navigationid2;//二级导航id
	private String mes;//咨询内容
	private Date constime;//咨询时间
	private String consphone;//咨询人手机号
	private String consname;//咨询人
	private Integer vipid;//会员ID
	private Integer flag;//处理标志 0：未处理，1：已处理
	private String resmes;//处理结果
	private Date createtime;//入库时间
	private Integer userid;//（处理人）最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getConsid(){
		return this.consid;
	}
	public void setConsid(Integer consid){
		this.consid=consid;
	}
	public Integer getNavigationid1(){
		return this.navigationid1;
	}
	public void setNavigationid1(Integer navigationid1){
		this.navigationid1=navigationid1;
	}
	public Integer getNavigationid2(){
		return this.navigationid2;
	}
	public void setNavigationid2(Integer navigationid2){
		this.navigationid2=navigationid2;
	}
	public String getMes(){
		return this.mes;
	}
	public void setMes(String mes){
		this.mes=mes;
	}
	public Date getConstime(){
		return this.constime;
	}
	public void setConstime(Date constime){
		this.constime=constime;
	}
	public String getConsphone(){
		return this.consphone;
	}
	public void setConsphone(String consphone){
		this.consphone=consphone;
	}
	public String getConsname(){
		return this.consname;
	}
	public void setConsname(String consname){
		this.consname=consname;
	}
	public Integer getVipid(){
		return this.vipid;
	}
	public void setVipid(Integer vipid){
		this.vipid=vipid;
	}
	public Integer getFlag(){
		return this.flag;
	}
	public void setFlag(Integer flag){
		this.flag=flag;
	}
	public String getResmes(){
		return this.resmes;
	}
	public void setResmes(String resmes){
		this.resmes=resmes;
	}
	public Date getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Date createtime){
		this.createtime=createtime;
	}
	public Integer getUserid(){
		return this.userid;
	}
	public void setUserid(Integer userid){
		this.userid=userid;
	}
	public Date getSavetime(){
		return this.savetime;
	}
	public void setSavetime(Date savetime){
		this.savetime=savetime;
	}

}