package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TSysStaff;

import java.util.List;

/**
 * Created by whq on 2016/3/21.
 */
public class NationalBean extends TSysStaff {
    private String queryFlag;//0：根据角色名模糊  1：根据姓名模糊查询
    private String queryUname;//查询条件：姓名
    private String queryRname;//查询条件：角色名
    private String savedateTimeStr;//更新时间long类型，但用字符串传递
    private String rolenames; // 字符串表示的角色名称，用“/”分隔
    private List<MenuBean> menus;//账户的菜单列表
    private String menuListStr; // 权限的集合（String类型,用“,”分隔）
    private String deptName;//机构名称
    private Integer hasCheckJobFlag;//是否含有检测任务 0：否  1：是
    private Integer hasJudgedFlag;//是否有专家研判 0：否 1：是
    private String ustatusStr;//状态

    private String nodetext;//菜单名

    public String getNodetext() {
        return nodetext;
    }

    public void setNodetext(String nodetext) {
        this.nodetext = nodetext;
    }

    public String getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getQueryUname() {
        return queryUname;
    }

    public void setQueryUname(String queryUname) {
        this.queryUname = queryUname;
    }

    public String getQueryRname() {
        return queryRname;
    }

    public void setQueryRname(String queryRname) {
        this.queryRname = queryRname;
    }

    public String getSavedateTimeStr() {
        return savedateTimeStr;
    }

    public void setSavedateTimeStr(String savedateTimeStr) {
        this.savedateTimeStr = savedateTimeStr;
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    public List<MenuBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuBean> menus) {
        this.menus = menus;
    }

    public String getMenuListStr() {
        return menuListStr;
    }

    public void setMenuListStr(String menuListStr) {
        this.menuListStr = menuListStr;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getHasCheckJobFlag() {
        return hasCheckJobFlag;
    }

    public void setHasCheckJobFlag(Integer hasCheckJobFlag) {
        this.hasCheckJobFlag = hasCheckJobFlag;
    }

    public Integer getHasJudgedFlag() {
        return hasJudgedFlag;
    }

    public void setHasJudgedFlag(Integer hasJudgedFlag) {
        this.hasJudgedFlag = hasJudgedFlag;
    }

    public String getUstatusStr() {
        return ustatusStr;
    }

    public void setUstatusStr(String ustatusStr) {
        this.ustatusStr = ustatusStr;
    }
}
