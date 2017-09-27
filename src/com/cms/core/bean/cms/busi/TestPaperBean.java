package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiTestpaper;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 浩 on 2017/9/17.
 */
public class TestPaperBean extends TBusiTestpaper {

    private int leveoneMin;
    private int leveoneMax;
    private int levetwoMin;
    private int levetwoMax;
    private int levelthreeMin;
    private int levelthreeMax;
    private int levelfourMin;
    private int levelfourMax;
    private int levelfiveMin;
    private int levelfiveMax;

    private String savetimestr;
    private String nodetext;//菜单名

    public String getNodetext() {
        return nodetext;
    }

    public void setNodetext(String nodetext) {
        this.nodetext = nodetext;
    }

    public String getSavetimestr() {
        return savetimestr;
    }

    public void setSavetimestr(String savetimestr) {
        this.savetimestr = savetimestr;
    }

    public int getLeveoneMin() {
        return leveoneMin;
    }

    public void setLeveoneMin(int leveoneMin) {
        this.leveoneMin = leveoneMin;
    }

    public int getLeveoneMax() {
        return leveoneMax;
    }

    public void setLeveoneMax(int leveoneMax) {
        this.leveoneMax = leveoneMax;
    }

    public int getLevetwoMin() {
        return levetwoMin;
    }

    public void setLevetwoMin(int levetwoMin) {
        this.levetwoMin = levetwoMin;
    }

    public int getLevetwoMax() {
        return levetwoMax;
    }

    public void setLevetwoMax(int levetwoMax) {
        this.levetwoMax = levetwoMax;
    }

    public int getLevelthreeMin() {
        return levelthreeMin;
    }

    public void setLevelthreeMin(int levelthreeMin) {
        this.levelthreeMin = levelthreeMin;
    }

    public int getLevelthreeMax() {
        return levelthreeMax;
    }

    public void setLevelthreeMax(int levelthreeMax) {
        this.levelthreeMax = levelthreeMax;
    }

    public int getLevelfourMax() {
        return levelfourMax;
    }

    public void setLevelfourMax(int levelfourMax) {
        this.levelfourMax = levelfourMax;
    }

    public int getLevelfourMin() {
        return levelfourMin;
    }

    public void setLevelfourMin(int levelfourMin) {
        this.levelfourMin = levelfourMin;
    }

    public int getLevelfiveMin() {
        return levelfiveMin;
    }

    public void setLevelfiveMin(int levelfiveMin) {
        this.levelfiveMin = levelfiveMin;
    }

    public int getLevelfiveMax() {
        return levelfiveMax;
    }

    public void setLevelfiveMax(int levelfiveMax) {
        this.levelfiveMax = levelfiveMax;
    }
}
