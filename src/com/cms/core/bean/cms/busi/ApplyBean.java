package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiApply;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 应聘信息
 */
public class ApplyBean extends TBusiApply {
    private String savetimestr;
    private String flagstr;
    private String educationstr;//应聘职业转换
    private String genderstr;//性别转换

    private String nodetext;//菜单名
    private MultipartFile applyfile;

    public MultipartFile getApplyfile() {
        return applyfile;
    }

    public void setApplyfile(MultipartFile applyfile) {
        this.applyfile = applyfile;
    }

    public String getNodetext() {
        return nodetext;
    }

    public void setNodetext(String nodetext) {
        this.nodetext = nodetext;
    }

    public String getEducationstr() {
        return educationstr;
    }

    public void setEducationstr(String educationstr) {
        this.educationstr = educationstr;
    }

    public String getGenderstr() {
        return genderstr;
    }

    public void setGenderstr(String genderstr) {
        this.genderstr = genderstr;
    }

    public String getFlagstr() {
        return flagstr;
    }

    public void setFlagstr(String flagstr) {
        this.flagstr = flagstr;
    }

    public String getSavetimestr() {
        return savetimestr;
    }

    public void setSavetimestr(String savetimestr) {
        this.savetimestr = savetimestr;
    }
}
