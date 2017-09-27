package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPnotice;
import com.cms.core.dao.foundcms.BusiPnoticeDao;
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
public class BusiPnoticeManagerImp implements BusiPnoticeManager {
    @Autowired
    private BusiPnoticeDao busiPnoticeDao;

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
    public TBusiPnotice getBusiPnotice(TBusiPnotice bean) {
        TBusiPnotice result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiPnoticeDao.getBusiPnotice(bean);
            if (result == null) {
                result = new TBusiPnotice();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiPnotice> list= busiPnoticeDao.getBusiPnoticeList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiPnotice tmp:list){
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
            result = new TBusiPnotice();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiPnotice> getBusiPnoticeList(TBusiPnotice bean) {
        List<TBusiPnotice> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPnoticeDao.getBusiPnoticeGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPnoticeDao.getBusiPnoticeList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPnotice>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPnotice>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPnoticeGroup(TBusiPnotice bean) {
        HashMap map =null;
        try{
            map = busiPnoticeDao.getBusiPnoticeGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPnotice addBusiPnotice(TBusiPnotice bean) {
        int cnt=busiPnoticeDao.addBusiPnotice(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPnotice updateBusiPnotice(TBusiPnotice bean) {
        TBusiPnotice result = new TBusiPnotice();
        int cnt=busiPnoticeDao.updateBusiPnotice(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPnotice deleteBusiPnotice(TBusiPnotice bean) {
        TBusiPnotice result = new TBusiPnotice();
        int cnt=busiPnoticeDao.deleteBusiPnotice(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public List<TBusiPnotice> getBusiPnoticeList4hot(TBusiPnotice bean) {
        List<TBusiPnotice> list=null;
        try {
            list = busiPnoticeDao.getBusiPnoticeList4hot(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPnotice>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPnotice>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }
}
