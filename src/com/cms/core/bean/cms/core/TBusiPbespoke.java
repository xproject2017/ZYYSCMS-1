package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;

	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：网上预约表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiPbespoke	 extends BaseBean {
	private Integer bespokeid;//主键，自增长
	private Integer navigationid1;//一级导航id
	private Integer navigationid2;//二级导航id
	private String productname;//产品名称
	private Date pbespoketime;//预约时间
	private String pbespokephone;//预约人手机号
	private String pbespokename;//预约人
	private Integer vipid;//会员ID
	private Integer flag;//处理标志 0：未处理，1：已处理
	private String resmes;//处理结果
	private Date createtime;//入库时间
	private Integer userid;//（处理人）最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getBespokeid(){
		return this.bespokeid;
	}
	public void setBespokeid(Integer bespokeid){
		this.bespokeid=bespokeid;
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
	public String getProductname(){
		return this.productname;
	}
	public void setProductname(String productname){
		this.productname=productname;
	}
	public Date getPbespoketime(){
		return this.pbespoketime;
	}
	public void setPbespoketime(Date pbespoketime){
		this.pbespoketime=pbespoketime;
	}
	public String getPbespokephone(){
		return this.pbespokephone;
	}
	public void setPbespokephone(String pbespokephone){
		this.pbespokephone=pbespokephone;
	}
	public String getPbespokename(){
		return this.pbespokename;
	}
	public void setPbespokename(String pbespokename){
		this.pbespokename=pbespokename;
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