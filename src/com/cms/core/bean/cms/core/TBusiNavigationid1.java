package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：一级导航表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiNavigationid1	 extends BaseBean {
	private Integer navigationid1;//一级导航id,主键，自增长
	private String navigationname1;//一级导航名
	public Integer getNavigationid1(){
		return this.navigationid1;
	}
	public void setNavigationid1(Integer navigationid1){
		this.navigationid1=navigationid1;
	}
	public String getNavigationname1(){
		return this.navigationname1;
	}
	public void setNavigationname1(String navigationname1){
		this.navigationname1=navigationname1;
	}

}