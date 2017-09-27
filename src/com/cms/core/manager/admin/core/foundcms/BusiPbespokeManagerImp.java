package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPbespoke;
import com.cms.core.dao.foundcms.BusiPbespokeDao;
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
public class BusiPbespokeManagerImp implements BusiPbespokeManager {
    @Autowired
    private BusiPbespokeDao busiPbespokeDao;

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
    public TBusiPbespoke getBusiPbespoke(TBusiPbespoke bean) {
        TBusiPbespoke result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiPbespokeDao.getBusiPbespoke(bean);
            if (result == null) {
                result = new TBusiPbespoke();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            if(null!=bean.getPrev_pageflag() && bean.getPrev_pageflag()==1){
                //需要在detail页面展示上一条和下一条
                List<TBusiPbespoke> list= busiPbespokeDao.getBusiPbespokeList4Next(bean); //查询不到数据返回null
                if(list!=null) {
                    if (list.size() > 0) {
                        for(TBusiPbespoke tmp:list){
                            if(tmp.getBespokeid()>bean.getBespokeid()){
                                //倒序
                                result.setPrev_id(tmp.getBespokeid());
                                result.setPrev_title(tmp.getProductname());
                            } else {
                                result.setNext_id(tmp.getBespokeid());
                                result.setNext_title(tmp.getProductname());
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
            result = new TBusiPbespoke();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiPbespoke> getBusiPbespokeList(TBusiPbespoke bean) {
        List<TBusiPbespoke> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPbespokeDao.getBusiPbespokeGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPbespokeDao.getBusiPbespokeList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPbespoke>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPbespoke>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPbespokeGroup(TBusiPbespoke bean) {
        HashMap map =null;
        try{
            map = busiPbespokeDao.getBusiPbespokeGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPbespoke addBusiPbespoke(TBusiPbespoke bean) {
        int cnt=busiPbespokeDao.addBusiPbespoke(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setBespokeid(bean.getBespokeid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPbespoke updateBusiPbespoke(TBusiPbespoke bean) {
        TBusiPbespoke result = new TBusiPbespoke();
        int cnt=busiPbespokeDao.updateBusiPbespoke(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPbespoke deleteBusiPbespoke(TBusiPbespoke bean) {
        TBusiPbespoke result = new TBusiPbespoke();
        int cnt=busiPbespokeDao.deleteBusiPbespoke(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
