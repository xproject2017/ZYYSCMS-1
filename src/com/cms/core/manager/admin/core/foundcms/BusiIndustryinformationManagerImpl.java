package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiIndustryinformation;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.dao.foundcms.BusiIndustryinformationDao;
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
public class BusiIndustryinformationManagerImpl implements BusiIndustryinformationManager {

    @Autowired
    private  BusiIndustryinformationDao busiIndustryinformationDao;

    @Override
    public TBusiIndustryinformation getBusiIndustryinformation(TBusiIndustryinformation bean) {
        return busiIndustryinformationDao.getBusiIndustryinformation(bean);
    }

    @Override
    public HashMap getBusiIndustryinformationGroup(TBusiIndustryinformation bean) {
        HashMap map =null;
        try{
            map = busiIndustryinformationDao.getBusiIndustryinformationGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public List<TBusiIndustryinformation> getBusiIndustryinformationList(TBusiIndustryinformation bean) {
        List<TBusiIndustryinformation> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiIndustryinformationDao.getBusiIndustryinformationGroup(bean);
                setPageInfo(bean, map);
            }
            list =  busiIndustryinformationDao.getBusiIndustryinformationList(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiIndustryinformation>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiIndustryinformation>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }

    }

    @Override
    public TBusiIndustryinformation addBusiIndustryinformation(TBusiIndustryinformation bean) {
        int index = busiIndustryinformationDao.addBusiIndustryinformation(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setMesgid(bean.getMesgid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiIndustryinformation updateBusiIndustryinformation(TBusiIndustryinformation bean) {
        TBusiIndustryinformation result = new TBusiIndustryinformation();
        int cnt=busiIndustryinformationDao.updateBusiIndustryinformation(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiIndustryinformation deleteBusiIndustryinformation(TBusiIndustryinformation bean) {
        TBusiIndustryinformation result = new TBusiIndustryinformation();
        int cnt=busiIndustryinformationDao.deleteBusiIndustryinformation(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
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
