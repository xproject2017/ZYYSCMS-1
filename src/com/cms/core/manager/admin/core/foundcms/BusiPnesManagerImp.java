package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPnes;
import com.cms.core.dao.foundcms.BusiPnesDao;
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
public class BusiPnesManagerImp implements BusiPnesManager {
    @Autowired
    private BusiPnesDao busiPnesDao;

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
    public TBusiPnes getBusiPnes(TBusiPnes bean) {
        TBusiPnes result =null;
        try {
            result = busiPnesDao.getBusiPnes(bean);
            if (result == null) {
                result = new TBusiPnes();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiPnes> list= busiPnesDao.getBusiPnesList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiPnes tmp:list){
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
            result = new TBusiPnes();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiPnes> getBusiPnesList(TBusiPnes bean) {
        List<TBusiPnes> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPnesDao.getBusiPnesGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPnesDao.getBusiPnesList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPnes>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPnes>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPnesGroup(TBusiPnes bean) {
        HashMap map =null;
        try{
            map = busiPnesDao.getBusiPnesGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPnes addBusiPnes(TBusiPnes bean) {
        int cnt=busiPnesDao.addBusiPnes(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPnes updateBusiPnes(TBusiPnes bean) {
        TBusiPnes result = new TBusiPnes();
        int cnt=busiPnesDao.updateBusiPnes(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPnes deleteBusiPnes(TBusiPnes bean) {
        TBusiPnes result = new TBusiPnes();
        int cnt=busiPnesDao.deleteBusiPnes(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public List<TBusiPnes> getBusiPnesList4hot(TBusiPnes bean) {
        List<TBusiPnes> list=null;
        try {
            list = busiPnesDao.getBusiPnesList4hot(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPnes>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPnes>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }
}
