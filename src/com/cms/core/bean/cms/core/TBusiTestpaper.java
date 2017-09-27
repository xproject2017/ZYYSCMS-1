package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;
	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：试卷表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiTestpaper	 extends BaseBean{
	private Integer tid;//主键，自增长
	private String etitle;//试卷名称
	private String scoringstandard;//评分标准JSON:{level1:{max:,min:,info:},level2:{max:,min:,info:},levelcnt:2}
	private Date createtime;//入库时间
	private Integer userid;//最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getTid(){
		return this.tid;
	}
	public void setTid(Integer tid){
		this.tid=tid;
	}
	public String getEtitle(){
		return this.etitle;
	}
	public void setEtitle(String etitle){
		this.etitle=etitle;
	}
	public String getScoringstandard(){
		return this.scoringstandard;
	}
	public void setScoringstandard(String scoringstandard){
		this.scoringstandard=scoringstandard;
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