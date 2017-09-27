package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiPconsultation;

/**
 * 产品咨询
 */
public class PconsultationBean extends TBusiPconsultation {
    private String savetimestr;
    private String constimestr;//咨询时间
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

    public String getConstimestr() {
        return constimestr;
    }

    public void setConstimestr(String constimestr) {
        this.constimestr = constimestr;
    }

    public String getSavetimestr() {
        return savetimestr;
    }

    public void setSavetimestr(String savetimestr) {
        this.savetimestr = savetimestr;
    }
}
