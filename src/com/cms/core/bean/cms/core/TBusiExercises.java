package com.cms.core.bean.cms.core;
import com.cms.core.bean.BaseBean;

import java.util.Date;
	/**
	*此类由MySQL2Bean工具自动生成
	*备注(数据表的comment字段)：题目表
	*@author zhouxx
	*@since 2017-09-04 18:20:19
	*/

	public class TBusiExercises	 extends BaseBean{
	private Integer eid;//主键，自增长
	private String etitle;//标题
		private Integer etype;
	private String option1;//选项1
	private String option2;//选项2
	private String option3;//选项3
	private String option4;//选项4
	private String option5;//选项5
	private String option6;//选项6
	private String option7;//选项7
	private String option8;//选项8
	private Integer option1score;//选项1分值
	private Integer option2score;//选项2分值
	private Integer option3score;//选项3分值
	private Integer option4score;//选项4分值
	private Integer option5score;//选项5分值
	private Integer option6score;//选项6分值
	private Integer option7score;//选项7分值
	private Integer option8score;//选项8分值
	private Date createtime;//入库时间
	private Integer userid;//最后一次操作的用户ID
	private Date savetime;//最后一次操作的时间
	public Integer getEid(){
		return this.eid;
	}
	public void setEid(Integer eid){
		this.eid=eid;
	}
	public String getEtitle(){
		return this.etitle;
	}
	public void setEtitle(String etitle){
		this.etitle=etitle;
	}

		public Integer getEtype() {
			return etype;
		}

		public void setEtype(Integer etype) {
			this.etype = etype;
		}

		public String getOption1(){
		return this.option1;
	}
	public void setOption1(String option1){
		this.option1=option1;
	}
	public String getOption2(){
		return this.option2;
	}
	public void setOption2(String option2){
		this.option2=option2;
	}
	public String getOption3(){
		return this.option3;
	}
	public void setOption3(String option3){
		this.option3=option3;
	}
	public String getOption4(){
		return this.option4;
	}
	public void setOption4(String option4){
		this.option4=option4;
	}
	public String getOption5(){
		return this.option5;
	}
	public void setOption5(String option5){
		this.option5=option5;
	}
	public String getOption6(){
		return this.option6;
	}
	public void setOption6(String option6){
		this.option6=option6;
	}
	public String getOption7(){
		return this.option7;
	}
	public void setOption7(String option7){
		this.option7=option7;
	}
	public String getOption8(){
		return this.option8;
	}
	public void setOption8(String option8){
		this.option8=option8;
	}
	public Integer getOption1score(){
		return this.option1score;
	}
	public void setOption1score(Integer option1score){
		this.option1score=option1score;
	}
	public Integer getOption2score(){
		return this.option2score;
	}
	public void setOption2score(Integer option2score){
		this.option2score=option2score;
	}
	public Integer getOption3score(){
		return this.option3score;
	}
	public void setOption3score(Integer option3score){
		this.option3score=option3score;
	}
	public Integer getOption4score(){
		return this.option4score;
	}
	public void setOption4score(Integer option4score){
		this.option4score=option4score;
	}
	public Integer getOption5score(){
		return this.option5score;
	}
	public void setOption5score(Integer option5score){
		this.option5score=option5score;
	}
	public Integer getOption6score(){
		return this.option6score;
	}
	public void setOption6score(Integer option6score){
		this.option6score=option6score;
	}
	public Integer getOption7score(){
		return this.option7score;
	}
	public void setOption7score(Integer option7score){
		this.option7score=option7score;
	}
	public Integer getOption8score(){
		return this.option8score;
	}
	public void setOption8score(Integer option8score){
		this.option8score=option8score;
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