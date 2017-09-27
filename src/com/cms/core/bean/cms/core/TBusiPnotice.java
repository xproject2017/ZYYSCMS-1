package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;
	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：公司公告信息表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiPnotice	 extends BaseBean{
	private Integer mesgid;//主键，自增长
	private Integer navigationid1;//一级导航id
	private Integer navigationid2;//二级导航id
	private Integer mbanner;//是否上大banner条 0：不启用，1：启用
	private Integer mmsgc;//是否上新闻中心 0：不启用，1：启用
	private String mtags;//关键字（站内搜索）,逗号分隔
	private String mtitle;//标题
	private String mes;//内容
	private String elliptical;//内容前100个字符
	private Date createtime;//入库时间
	private Integer userid;//最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getMesgid(){
		return this.mesgid;
	}
	public void setMesgid(Integer mesgid){
		this.mesgid=mesgid;
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
	public Integer getMbanner(){
		return this.mbanner;
	}
	public void setMbanner(Integer mbanner){
		this.mbanner=mbanner;
	}
	public Integer getMmsgc(){
		return this.mmsgc;
	}
	public void setMmsgc(Integer mmsgc){
		this.mmsgc=mmsgc;
	}
	public String getMtags(){
		return this.mtags;
	}
	public void setMtags(String mtags){
		this.mtags=mtags;
	}
	public String getMtitle(){
		return this.mtitle;
	}
	public void setMtitle(String mtitle){
		this.mtitle=mtitle;
	}
	public String getMes(){
		return this.mes;
	}
	public void setMes(String mes){
		this.mes=mes;
	}
	public String getElliptical(){
		return this.elliptical;
	}
	public void setElliptical(String elliptical){
		this.elliptical=elliptical;
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