package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：角色菜单模板表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TSysRolemenus	 extends BaseBean{
	private Integer id;//id,主键，自增长
	private Integer roleid;//角色id
	private Integer nodeid;//菜单id
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getRoleid(){
		return this.roleid;
	}
	public void setRoleid(Integer roleid){
		this.roleid=roleid;
	}
	public Integer getNodeid(){
		return this.nodeid;
	}
	public void setNodeid(Integer nodeid){
		this.nodeid=nodeid;
	}

}