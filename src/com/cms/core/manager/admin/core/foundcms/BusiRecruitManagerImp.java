package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiRecruit;
import com.cms.core.dao.foundcms.BusiRecruitDao;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BusiRecruitManagerImp implements BusiRecruitManager {
    @Autowired
    private BusiRecruitDao busiRecruitDao;

    private void setPageInfo(BaseBean baseBean,HashMap hashMap) {
        if (baseBean.getCurrentPage() == null) { //检查当前页
            baseBean.setCurrentPage(0);
        }
        if (baseBean.getPageSize() == null) { //检查每页数量
            baseBean.setPageSize(20);
        }
        baseBean.setCurrentSize(baseBean.getCurrentPage() * baseBean.getPageSize());
        baseBean.setTotalNum(Integer.valueOf(String.valueOf(hashMap.get("CNT"))));
    }

    @Override
    public TBusiRecruit getBusiRecruit(TBusiRecruit bean) {
        TBusiRecruit result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiRecruitDao.getBusiRecruit(bean);
            if (result == null) {
                result = new TBusiRecruit();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiRecruit> list= busiRecruitDao.getBusiRecruitList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiRecruit tmp:list){
                            if(tmp.getMesgid()>bean.getMesgid()){
                                //倒序
                                result.setPrev_id(tmp.getMesgid());
                                result.setPrev_title(tmp.getMtitle());
                            } else {
                                result.setNext_id(tmp.getMesgid());
                                result.setNext_title(tmp.getMtitle());
                            }
                        }
                        result.setNgid1(bean.getNavigationid1());
                        result.setNgid2(bean.getNavigationid2());
                    }
                }
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TBusiRecruit();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiRecruit> getBusiRecruitList(TBusiRecruit bean) {
        List<TBusiRecruit> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiRecruitDao.getBusiRecruitGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiRecruitDao.getBusiRecruitList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiRecruit>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiRecruit>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiRecruitGroup(TBusiRecruit bean) {
        HashMap map =null;
        try{
            map = busiRecruitDao.getBusiRecruitGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiRecruit addBusiRecruit(TBusiRecruit bean) {
        int cnt=busiRecruitDao.addBusiRecruit(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiRecruit updateBusiRecruit(TBusiRecruit bean) {
        TBusiRecruit result = new TBusiRecruit();
        int cnt=busiRecruitDao.updateBusiRecruit(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiRecruit deleteBusiRecruit(TBusiRecruit bean) {
        TBusiRecruit result = new TBusiRecruit();
        int cnt=busiRecruitDao.deleteBusiRecruit(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
