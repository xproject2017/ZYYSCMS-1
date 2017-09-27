package com.cms.core.bean.cms.core;

import com.cms.core.bean.BaseBean;

import java.util.Date;

/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：应聘信息表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiApply	 extends BaseBean {
	private Integer id;//主键，自增长
	private String applyposition;//应聘职位
	private Date applytime;//应聘时间
	private String applyphone;//应聘人手机号
	private String applyemail;//应聘人邮箱
	private String applyaddress;//应聘人联系住址
	private String applyname;//应聘人
	private Integer gender;//性别 0男 1女
	private String birthday;//出生年月
	private String unative;//籍贯
	private Integer education;//学历/学位  0初中 1高中 2大专 3本科 4硕士 5博士
	private String graduateschool;//毕业学校
	private String profession;//专业
	private String graduatetime;//毕业时间
	private String expectedsalary;//期望月薪
	private String contractexpectation;//合同期望
	private Integer flag;//处理标志 0：未处理，1：已处理
	private String resmes;//处理结果
	private Date createtime;//入库时间
	private Integer userid;//（处理人）最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getApplyposition(){
		return this.applyposition;
	}
	public void setApplyposition(String applyposition){
		this.applyposition=applyposition;
	}
	public Date getApplytime(){
		return this.applytime;
	}
	public void setApplytime(Date applytime){
		this.applytime=applytime;
	}
	public String getApplyphone(){
		return this.applyphone;
	}
	public void setApplyphone(String applyphone){
		this.applyphone=applyphone;
	}
	public String getApplyemail(){
		return this.applyemail;
	}
	public void setApplyemail(String applyemail){
		this.applyemail=applyemail;
	}
	public String getApplyaddress(){
		return this.applyaddress;
	}
	public void setApplyaddress(String applyaddress){
		this.applyaddress=applyaddress;
	}
	public String getApplyname(){
		return this.applyname;
	}
	public void setApplyname(String applyname){
		this.applyname=applyname;
	}
	public Integer getGender(){
		return this.gender;
	}
	public void setGender(Integer gender){
		this.gender=gender;
	}
	public String getBirthday(){
		return this.birthday;
	}
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public String getUnative(){
		return this.unative;
	}
	public void setUnative(String unative){
		this.unative=unative;
	}
	public Integer getEducation(){
		return this.education;
	}
	public void setEducation(Integer education){
		this.education=education;
	}
	public String getGraduateschool(){
		return this.graduateschool;
	}
	public void setGraduateschool(String graduateschool){
		this.graduateschool=graduateschool;
	}
	public String getProfession(){
		return this.profession;
	}
	public void setProfession(String profession){
		this.profession=profession;
	}
	public String getGraduatetime(){
		return this.graduatetime;
	}
	public void setGraduatetime(String graduatetime){
		this.graduatetime=graduatetime;
	}
	public String getExpectedsalary(){
		return this.expectedsalary;
	}
	public void setExpectedsalary(String expectedsalary){
		this.expectedsalary=expectedsalary;
	}
	public String getContractexpectation(){
		return this.contractexpectation;
	}
	public void setContractexpectation(String contractexpectation){
		this.contractexpectation=contractexpectation;
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