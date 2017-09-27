package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPconsultation;
import com.cms.core.dao.foundcms.BusiPconsultationDao;
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
public class BusiPconsultationManagerImp implements BusiPconsultationManager {
    @Autowired
    private BusiPconsultationDao busiPconsultationDao;

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
    public TBusiPconsultation getBusiPconsultation(TBusiPconsultation bean) {
        TBusiPconsultation result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiPconsultationDao.getBusiPconsultation(bean);
            if (result == null) {
                result = new TBusiPconsultation();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiPconsultation> list= busiPconsultationDao.getBusiPconsultationList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiPconsultation tmp:list){
                            if(tmp.getConsid()>bean.getConsid()){
                                //倒序
                                result.setPrev_id(tmp.getConsid());
                                result.setPrev_title(tmp.getConsname());

                            } else {
                                result.setNext_id(tmp.getConsid());
                                result.setNext_title(tmp.getConsname());
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
            result = new TBusiPconsultation();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiPconsultation> getBusiPconsultationList(TBusiPconsultation bean) {
        List<TBusiPconsultation> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPconsultationDao.getBusiPconsultationGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPconsultationDao.getBusiPconsultationList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPconsultation>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPconsultation>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPconsultationGroup(TBusiPconsultation bean) {
        HashMap map =null;
        try{
            map = busiPconsultationDao.getBusiPconsultationGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPconsultation addBusiPconsultation(TBusiPconsultation bean) {
        int cnt=busiPconsultationDao.addBusiPconsultation(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setConsid(bean.getConsid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPconsultation updateBusiPconsultation(TBusiPconsultation bean) {
        TBusiPconsultation result = new TBusiPconsultation();
        int cnt=busiPconsultationDao.updateBusiPconsultation(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPconsultation deleteBusiPconsultation(TBusiPconsultation bean) {
        TBusiPconsultation result = new TBusiPconsultation();
        int cnt=busiPconsultationDao.deleteBusiPconsultation(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
