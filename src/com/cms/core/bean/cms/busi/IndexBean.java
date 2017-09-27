package com.cms.core.bean.cms.busi;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by lenovo on 2016/5/13.
 */
public class IndexBean {
    private Integer navigationid1;//一级导航id
    private Integer navigationid2;//二级导航id
    private String nodetext;//菜单名
    private String filename;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getNodetext() {
        return nodetext;
    }

    public void setNodetext(String nodetext) {
        this.nodetext = nodetext;
    }

    public Integer getNavigationid1() {
        return navigationid1;
    }

    public void setNavigationid1(Integer navigationid1) {
        this.navigationid1 = navigationid1;
    }

    public Integer getNavigationid2() {
        return navigationid2;
    }

    public void setNavigationid2(Integer navigationid2) {
        this.navigationid2 = navigationid2;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

