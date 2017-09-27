package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.busi.Navigationid;
import com.cms.core.bean.cms.core.TBusiNavigationid1;
import com.cms.core.bean.cms.core.TBusiNavigationid2;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.dao.foundcms.BusiNavigationid1Dao;
import com.cms.core.dao.foundcms.BusiNavigationid2Dao;
import com.cms.core.dao.system.SysMenuDao;
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
public class BusiNavigationid1ManagerImp implements BusiNavigationid1Manager {
    @Autowired
    private BusiNavigationid1Dao busiNavigationid1Dao;
    @Autowired
    private BusiNavigationid2Dao busiNavigationid2Dao;
    @Autowired
    private SysMenuDao sysMenuDao;

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
    public TBusiNavigationid1 getBusiNavigationid1(TBusiNavigationid1 bean) {
        TBusiNavigationid1 result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiNavigationid1Dao.getBusiNavigationid1(bean);
            if (result == null) {
                result = new TBusiNavigationid1();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TBusiNavigationid1();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiNavigationid1> getBusiNavigationid1List(TBusiNavigationid1 bean) {
        List<TBusiNavigationid1> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiNavigationid1Dao.getBusiNavigationid1Group(bean);
                setPageInfo(bean, map);
            }
            list = busiNavigationid1Dao.getBusiNavigationid1List(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiNavigationid1>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiNavigationid1>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiNavigationid1Group(TBusiNavigationid1 bean) {
        HashMap map =null;
        try{
            map = busiNavigationid1Dao.getBusiNavigationid1Group(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiNavigationid1 addBusiNavigationid1(TBusiNavigationid1 bean) {
        int cnt=busiNavigationid1Dao.addBusiNavigationid1(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setNavigationid1(bean.getNavigationid1());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiNavigationid1 updateBusiNavigationid1(TBusiNavigationid1 bean) {
        TBusiNavigationid1 result = new TBusiNavigationid1();
        int cnt=busiNavigationid1Dao.updateBusiNavigationid1(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiNavigationid1 updateBusiNavigationid(Navigationid listbean) {
        TBusiNavigationid1 result = new TBusiNavigationid1();
        TSysMenu updateMenuName=null;
        int cnt=0;
        for(TBusiNavigationid1 bean:listbean.getBusiNavigationid1()) {
             cnt += busiNavigationid1Dao.updateBusiNavigationid1(bean);
            updateMenuName=new TSysMenu();
            updateMenuName.setNavigationid1(bean.getNavigationid1());
            updateMenuName.setNavigationid2(0);
            updateMenuName.setNodetext(bean.getNavigationname1());
            cnt += sysMenuDao.updateSysMenuByNavigationid(updateMenuName);
        }
        for(TBusiNavigationid2 bean:listbean.getBusiNavigationid2()) {
             cnt += busiNavigationid2Dao.updateBusiNavigationid2(bean);
            updateMenuName=new TSysMenu();
            updateMenuName.setNavigationid1(bean.getNavigationid1());
            updateMenuName.setNavigationid2(bean.getNavigationid2());
            updateMenuName.setNodetext(bean.getNavigationname2());
            cnt += sysMenuDao.updateSysMenuByNavigationid(updateMenuName);
        }
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiNavigationid1 deleteBusiNavigationid1(TBusiNavigationid1 bean) {
        TBusiNavigationid1 result = new TBusiNavigationid1();
        int cnt=busiNavigationid1Dao.deleteBusiNavigationid1(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
