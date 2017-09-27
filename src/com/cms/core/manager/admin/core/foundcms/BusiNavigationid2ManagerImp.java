package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiNavigationid2;
import com.cms.core.dao.foundcms.BusiNavigationid2Dao;
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
public class BusiNavigationid2ManagerImp implements BusiNavigationid2Manager {
    @Autowired
    private BusiNavigationid2Dao busiNavigationid2Dao;

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
    public TBusiNavigationid2 getBusiNavigationid2(TBusiNavigationid2 bean) {
        TBusiNavigationid2 result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiNavigationid2Dao.getBusiNavigationid2(bean);
            if (result == null) {
                result = new TBusiNavigationid2();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TBusiNavigationid2();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiNavigationid2> getBusiNavigationid2List(TBusiNavigationid2 bean) {
        List<TBusiNavigationid2> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiNavigationid2Dao.getBusiNavigationid2Group(bean);
                setPageInfo(bean, map);
            }
            list = busiNavigationid2Dao.getBusiNavigationid2List(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiNavigationid2>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiNavigationid2>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiNavigationid2Group(TBusiNavigationid2 bean) {
        HashMap map =null;
        try{
            map = busiNavigationid2Dao.getBusiNavigationid2Group(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiNavigationid2 addBusiNavigationid2(TBusiNavigationid2 bean) {
        int cnt=busiNavigationid2Dao.addBusiNavigationid2(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setNavigationid2(bean.getNavigationid2());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiNavigationid2 updateBusiNavigationid2(TBusiNavigationid2 bean) {
        TBusiNavigationid2 result = new TBusiNavigationid2();
        int cnt=busiNavigationid2Dao.updateBusiNavigationid2(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiNavigationid2 deleteBusiNavigationid2(TBusiNavigationid2 bean) {
        TBusiNavigationid2 result = new TBusiNavigationid2();
        int cnt=busiNavigationid2Dao.deleteBusiNavigationid2(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
