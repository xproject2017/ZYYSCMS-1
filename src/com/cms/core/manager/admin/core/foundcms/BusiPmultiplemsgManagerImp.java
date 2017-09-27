package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPmultiplemsg;
import com.cms.core.dao.foundcms.BusiPmultiplemsgDao;
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
public class BusiPmultiplemsgManagerImp implements BusiPmultiplemsgManager {
    @Autowired
    private BusiPmultiplemsgDao busiPmultiplemsgDao;

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
    public TBusiPmultiplemsg getBusiPmultiplemsg(TBusiPmultiplemsg bean) {
        TBusiPmultiplemsg result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiPmultiplemsgDao.getBusiPmultiplemsg(bean);
            if (result == null) {
                result = new TBusiPmultiplemsg();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiPmultiplemsg> list= busiPmultiplemsgDao.getBusiPmultiplemsgList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiPmultiplemsg tmp:list){
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
            result = new TBusiPmultiplemsg();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiPmultiplemsg> getBusiPmultiplemsgList(TBusiPmultiplemsg bean) {
        List<TBusiPmultiplemsg> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPmultiplemsgDao.getBusiPmultiplemsgGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPmultiplemsgDao.getBusiPmultiplemsgList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPmultiplemsg>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPmultiplemsg>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPmultiplemsgGroup(TBusiPmultiplemsg bean) {
        HashMap map =null;
        try{
            map = busiPmultiplemsgDao.getBusiPmultiplemsgGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPmultiplemsg addBusiPmultiplemsg(TBusiPmultiplemsg bean) {
        int cnt=busiPmultiplemsgDao.addBusiPmultiplemsg(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPmultiplemsg updateBusiPmultiplemsg(TBusiPmultiplemsg bean) {
        TBusiPmultiplemsg result = new TBusiPmultiplemsg();
        int cnt=busiPmultiplemsgDao.updateBusiPmultiplemsg(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPmultiplemsg deleteBusiPmultiplemsg(TBusiPmultiplemsg bean) {
        TBusiPmultiplemsg result = new TBusiPmultiplemsg();
        int cnt=busiPmultiplemsgDao.deleteBusiPmultiplemsg(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public List<TBusiPmultiplemsg> getBusiPmultiplemsgList4admin(TBusiPmultiplemsg bean) {
        List<TBusiPmultiplemsg> list=null;
        try {
            list = busiPmultiplemsgDao.getBusiPmultiplemsgList4admin(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                }else {
                    list=new ArrayList<TBusiPmultiplemsg>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPmultiplemsg>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public List<TBusiPmultiplemsg> getBusiPmultiplemsgList4cms(TBusiPmultiplemsg bean) {
        List<TBusiPmultiplemsg> list=null;
        try {
            list = busiPmultiplemsgDao.getBusiPmultiplemsgList4cms(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                }else {
                    list=new ArrayList<TBusiPmultiplemsg>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPmultiplemsg>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public List<TBusiPmultiplemsg> getBusiPmultiplemsgList4search(TBusiPmultiplemsg bean) {
        List<TBusiPmultiplemsg> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPmultiplemsgDao.getBusiPmultiplemsgList4searchGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPmultiplemsgDao.getBusiPmultiplemsgList4search(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPmultiplemsg>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPmultiplemsg>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPmultiplemsgList4searchGroup(TBusiPmultiplemsg bean) {
        HashMap map =null;
        try{
            map = busiPmultiplemsgDao.getBusiPmultiplemsgList4searchGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }
}
