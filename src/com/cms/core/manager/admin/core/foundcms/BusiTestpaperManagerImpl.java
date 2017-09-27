package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.bean.cms.core.TBusiTestpaper;
import com.cms.core.dao.foundcms.BusiTestpaperDao;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
@Service
@Transactional
public class BusiTestpaperManagerImpl implements BusiTestpaperManager {
    @Autowired
    private BusiTestpaperDao busiTestpaperDao;

    @Override
    public TBusiTestpaper getBusiTestpaper(TBusiTestpaper bean) {
        return busiTestpaperDao.getBusiTestpaper(bean);
    }

    @Override
    public HashMap getBusiTestpaperGroup(TBusiTestpaper bean) {
        HashMap map =null;
        try{
            map = busiTestpaperDao.getBusiTestpaperGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public List<TBusiTestpaper> getBusiTestpaperList(TBusiTestpaper bean) {
        List<TBusiTestpaper> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiTestpaperDao.getBusiTestpaperGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiTestpaperDao.getBusiTestpaperList(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiTestpaper>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiTestpaper>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public TBusiTestpaper addBusiTestpaper(TBusiTestpaper bean) {
        int index = busiTestpaperDao.addBusiTestpaper(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setTid(bean.getTid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiTestpaper updateBusiTestpaper(TBusiTestpaper bean) {
        TBusiTestpaper result = new TBusiTestpaper();
        int cnt=busiTestpaperDao.updateBusiTestpaper(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiTestpaper deleteBusiTestpaper(TBusiTestpaper bean) {
        TBusiTestpaper result = new TBusiTestpaper();
        int cnt=busiTestpaperDao.deleteBusiTestpaper(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public List<TBusiExercises> getExercises(TBusiTestpaper bean) {
        List<TBusiExercises> list=null;
        TBusiExercises newBean = new TBusiExercises();
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiTestpaperDao.getBusiTestpaperGroup(bean);
                setPageInfo(bean, map);
            }

            list = busiTestpaperDao.getExercises(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiExercises>();
                    newBean.setResult(ResultInfo.NullOutput);
                    newBean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(newBean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiExercises>();
            newBean.setResult(ResultInfo.Fail);
            newBean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(newBean);
            return list;
        }
    }

    @Override
    public List<TBusiExercises> getNoexercises(TBusiTestpaper bean) {
        List<TBusiExercises> list=null;
        TBusiExercises newBean = new TBusiExercises();
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiTestpaperDao.getBusiTestpaperGroup(bean);
                setPageInfo(bean, map);
            }

            list = busiTestpaperDao.getNoexercises(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiExercises>();
                    newBean.setResult(ResultInfo.NullOutput);
                    newBean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(newBean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiExercises>();
            newBean.setResult(ResultInfo.Fail);
            newBean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(newBean);
            return list;
        }
    }

    private void setPageInfo(BaseBean baseBean, HashMap hashMap) {
        if (baseBean.getCurrentPage() == null) { //检查当前页
            baseBean.setCurrentPage(0);
        }
        if (baseBean.getPageSize() == null) { //检查每页数量
            baseBean.setPageSize(20);
        }
        baseBean.setCurrentSize(baseBean.getCurrentPage() * baseBean.getPageSize());
        baseBean.setTotalNum(Integer.valueOf(String.valueOf(hashMap.get("CNT"))));
    }
}
