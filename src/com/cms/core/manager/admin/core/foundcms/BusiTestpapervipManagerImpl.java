package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiTestpapervip;
import com.cms.core.dao.foundcms.BusiTestpapervipDao;
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
public class BusiTestpapervipManagerImpl implements BusiTestpapervipManager {


    @Autowired
    private BusiTestpapervipDao busiTestpapervipDao;


    @Override
    public TBusiTestpapervip getBusiTestpapervip(TBusiTestpapervip bean) {
        return null;
    }

    @Override
    public HashMap getBusiTestpapervipGroup(TBusiTestpapervip bean) {
        return null;
    }

    @Override
    public List<TBusiTestpapervip> getBusiTestpapervipList(TBusiTestpapervip bean) {
        return null;
    }

    @Override
    public TBusiTestpapervip addBusiTestpapervip(TBusiTestpapervip bean) {
        int index = busiTestpapervipDao.addBusiTestpapervip(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setId(bean.getId());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiTestpapervip updateBusiTestpapervip(TBusiTestpapervip bean) {
        return null;
    }

    @Override
    public TBusiTestpapervip deleteBusiTestpapervip(TBusiTestpapervip bean) {
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
