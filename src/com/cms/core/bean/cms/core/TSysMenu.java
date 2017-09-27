package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：菜单表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TSysMenu	 extends BaseBean{
	private Integer nodeid;//菜单id,主键
	private Integer nodelevel;//1：一级菜单，2：二级菜单，3：三级菜单
	private String nodecode;//菜单编码 000000
	private String nodetext;//菜单名
	private String path;//菜单URL
	private Integer mtype;//0后台菜单，1门户菜单
	private Integer navigationid1;//一级导航id
	private Integer navigationid2;//二级导航id
	public Integer getNodeid(){
		return this.nodeid;
	}
	public void setNodeid(Integer nodeid){
		this.nodeid=nodeid;
	}
	public Integer getNodelevel(){
		return this.nodelevel;
	}
	public void setNodelevel(Integer nodelevel){
		this.nodelevel=nodelevel;
	}
	public String getNodecode(){
		return this.nodecode;
	}
	public void setNodecode(String nodecode){
		this.nodecode=nodecode;
	}
	public String getNodetext(){
		return this.nodetext;
	}
	public void setNodetext(String nodetext){
		this.nodetext=nodetext;
	}
	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path=path;
	}
	public Integer getMtype(){
		return this.mtype;
	}
	public void setMtype(Integer mtype){
		this.mtype=mtype;
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

}