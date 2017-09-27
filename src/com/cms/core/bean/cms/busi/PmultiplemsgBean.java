package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiPmultiplemsg;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公司综合信息
 */
public class PmultiplemsgBean extends TBusiPmultiplemsg{
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

}
