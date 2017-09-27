package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiVip;
import com.cms.core.dao.foundcms.BusiVipDao;
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
public class BusiVipManagerImp implements BusiVipManager {
    @Autowired
    private BusiVipDao busiVipDao;

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
    public TBusiVip getBusiVip(TBusiVip bean) {
        TBusiVip result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = busiVipDao.getBusiVip(bean);
            if (result == null) {
                result = new TBusiVip();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TBusiVip();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TBusiVip> getBusiVipList(TBusiVip bean) {
        List<TBusiVip> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiVipDao.getBusiVipGroup(bean);
                setPageInfo(bean, map);
            }
            list = busiVipDao.getBusiVipList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiVip>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiVip>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getBusiVipGroup(TBusiVip bean) {
        HashMap map =null;
        try{
            map = busiVipDao.getBusiVipGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TBusiVip addBusiVip(TBusiVip bean) {
        int cnt=busiVipDao.addBusiVip(bean);
        bean.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setVipid(bean.getVipid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiVip updateBusiVip(TBusiVip bean) {
        TBusiVip result = new TBusiVip();
        int cnt=busiVipDao.updateBusiVip(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiVip deleteBusiVip(TBusiVip bean) {
        TBusiVip result = new TBusiVip();
        int cnt=busiVipDao.deleteBusiVip(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
