package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiIndustryinformation;
import com.cms.core.dao.foundcms.BusiExercisesDao;
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
public class BusiExercisesManagerImpl implements BusiExercisesManager {

    @Autowired
    private BusiExercisesDao busiExercisesDao;

    @Override
    public TBusiExercises getBusiExercises(TBusiExercises bean) {
        return busiExercisesDao.getBusiExercises(bean);
    }

    @Override
    public HashMap getBusiExercisesGroup(TBusiExercises bean) {
        HashMap map =null;
        try{
            map = busiExercisesDao.getBusiExercisesGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public List<TBusiExercises> getBusiExercisesList(TBusiExercises bean) {
        List<TBusiExercises> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = busiExercisesDao.getBusiExercisesGroup(bean);
                setPageInfo(bean, map);
            }
            list =  busiExercisesDao.getBusiExercisesList(bean);//查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TBusiExercises>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TBusiExercises>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public TBusiExercises addBusiExercises(TBusiExercises bean) {
        int index = busiExercisesDao.addBusiExercises(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setEid(bean.getEid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiExercises updateBusiExercises(TBusiExercises bean) {
        TBusiExercises result = new TBusiExercises();
        int cnt=busiExercisesDao.updateBusiExercises(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TBusiExercises deleteBusiExercises(TBusiExercises bean) {
        TBusiExercises result = new TBusiExercises();
        int cnt=busiExercisesDao.deleteBusiExercises(bean);
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
