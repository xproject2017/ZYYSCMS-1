package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiPnotice;
import com.cms.core.bean.cms.core.TBusiPproduct;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lenovo on 2016/5/11.
 */
public class PnoticeBean extends TBusiPnotice {
    private String savetimestr;
    private MultipartFile fileimage;
    private String nodetext;//菜单名

    public String getNodetext() {
        return nodetext;
    }

    public void setNodetext(String nodetext) {
        this.nodetext = nodetext;
    }
    public MultipartFile getFileimage() {
        return fileimage;
    }

    public void setFileimage(MultipartFile fileimage) {
        this.fileimage = fileimage;
    }

    public String getSavetimestr() {
        return savetimestr;
    }

    public void setSavetimestr(String savetimestr) {
        this.savetimestr = savetimestr;
    }
}
