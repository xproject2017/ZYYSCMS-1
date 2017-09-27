package com.cms.core.bean.cms.busi;
import com.cms.core.bean.cms.core.TBusiPbespoke;


/**
*网上预约
*/

public class PbespokeBean extends TBusiPbespoke {
	private String savetimestr;
	private String pbespoketimestr;//预约时间
	private String flagstr;

	private String nodetext;//菜单名

	public String getNodetext() {
		return nodetext;
	}

	public void setNodetext(String nodetext) {
		this.nodetext = nodetext;
	}

	public String getFlagstr() {
		return flagstr;
	}

	public void setFlagstr(String flagstr) {
		this.flagstr = flagstr;
	}

	public String getPbespoketimestr() {
		return pbespoketimestr;
	}

	public void setPbespoketimestr(String pbespoketimestr) {
		this.pbespoketimestr = pbespoketimestr;
	}

	public String getSavetimestr() {
		return savetimestr;
	}

	public void setSavetimestr(String savetimestr) {
		this.savetimestr = savetimestr;
	}
}