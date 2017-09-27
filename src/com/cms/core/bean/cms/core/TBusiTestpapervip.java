package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：会员答题表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiTestpapervip	 extends BaseBean {
	private Integer id;//主键，自增长
	private Integer tid;//试卷ID
	private Integer vipid;//会员ID
	private Integer score;//分值
	private String tinfo;//评估结果
	private Date createtime;//入库时间
	private Integer userid;//最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getTid(){
		return this.tid;
	}
	public void setTid(Integer tid){
		this.tid=tid;
	}
	public Integer getVipid(){
		return this.vipid;
	}
	public void setVipid(Integer vipid){
		this.vipid=vipid;
	}
	public Integer getScore(){
		return this.score;
	}
	public void setScore(Integer score){
		this.score=score;
	}
	public String getTinfo(){
		return this.tinfo;
	}
	public void setTinfo(String tinfo){
		this.tinfo=tinfo;
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