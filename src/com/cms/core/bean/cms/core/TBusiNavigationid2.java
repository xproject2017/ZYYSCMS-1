package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：二级导航表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiNavigationid2	 extends BaseBean{
	private Integer navigationid2;//二级导航id,主键，自增长
	private Integer navigationid1;//一级导航id
	private String navigationname2;//二级导航名
	public Integer getNavigationid2(){
		return this.navigationid2;
	}
	public void setNavigationid2(Integer navigationid2){
		this.navigationid2=navigationid2;
	}
	public Integer getNavigationid1(){
		return this.navigationid1;
	}
	public void setNavigationid1(Integer navigationid1){
		this.navigationid1=navigationid1;
	}
	public String getNavigationname2(){
		return this.navigationname2;
	}
	public void setNavigationname2(String navigationname2){
		this.navigationname2=navigationname2;
	}

}