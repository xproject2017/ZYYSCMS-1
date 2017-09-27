package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiPproduct;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.dao.foundcms.BusiPproductcaseDao;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/10.
 */
@Service
@Transactional
public class BusiPproductCaseManagerImpl implements  BusiPproductCaseManager {

    @Autowired
    private BusiPproductcaseDao busiPproductcaseDao;
    @Override
    public TBusiPproductcase getBusiPproductCase(TBusiPproductcase bean) {
        return busiPproductcaseDao.getBusiPproductcase(bean);
    }

    @Override
    public List<TBusiPproductcase> getBusiPproductCaseList(TBusiPproductcase bean) {
        List<TBusiPproductcase> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiPproductcaseDao.getBusiPproductcaseGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiPproductcaseDao.getBusiPproductcaseList(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiPproductcase>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiPproductcase>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiPproductCaseGroup(TBusiPproductcase bean) {
        HashMap map =null;
        try{
            map = busiPproductcaseDao.getBusiPproductcaseGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiPproductcase addBusiPproductCase(TBusiPproductcase bean) {
        int index = busiPproductcaseDao.addBusiPproductcase(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiPproductcase updateBusiPproductCase(TBusiPproductcase bean) {
        TBusiPproductcase result = new TBusiPproductcase();
        int cnt=busiPproductcaseDao.updateBusiPproductcase(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiPproductcase deleteBusiPproductCase(TBusiPproductcase bean) {
        TBusiPproductcase result = new TBusiPproductcase();
        int cnt=busiPproductcaseDao.deleteBusiPproductcase(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public List<TBusiPproductcase> getBusiPproductCaseList4hot(TBusiPproductcase bean) {
       return null;
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
