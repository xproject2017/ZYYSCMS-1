package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：客服QQ号表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
*/

	public class TBusiCserrviceqq	 extends BaseBean {
	private Integer id;//主键，自增长
	private String qq;//QQ号
	private String cname;//客服名称
	private Date createtime;//入库时间
	private Integer userid;//最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getQq(){
		return this.qq;
	}
	public void setQq(String qq){
		this.qq=qq;
	}
	public String getCname(){
		return this.cname;
	}
	public void setCname(String cname){
		this.cname=cname;
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