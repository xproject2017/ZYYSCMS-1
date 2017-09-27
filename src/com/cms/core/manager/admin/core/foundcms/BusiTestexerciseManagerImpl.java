package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiTestexercise;
import com.cms.core.bean.cms.core.TBusiTestpaper;
import com.cms.core.dao.foundcms.BusiTestexerciseDao;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */

@Service
@Transactional
public class BusiTestexerciseManagerImpl implements BusiTestexerciseManager {

    @Autowired
    private BusiTestexerciseDao busiTestexerciseDao;

    @Override
    public TBusiTestexercise getBusiTestexercise(TBusiTestexercise bean) {
        return null;
    }

    @Override
    public HashMap getBusiTestexerciseGroup(TBusiTestexercise bean) {
        return null;
    }

    @Override
    public List<TBusiTestexercise> getBusiTestexerciseList(TBusiTestexercise bean) {
        return null;
    }

    @Override
    public TBusiTestexercise addBusiTestexercise(TBusiTestexercise bean) {
        int index = busiTestexerciseDao.addBusiTestexercise(bean);
        bean.setDbcnt(index);
        if(index!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        bean.setTid(bean.getTid());
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TBusiTestexercise updateBusiTestexercise(TBusiTestexercise bean) {
        return null;
    }

    @Override
    public TBusiTestexercise deleteBusiTestexercise(TBusiTestexercise bean) {
        TBusiTestexercise result = new TBusiTestexercise();
        int cnt=busiTestexerciseDao.deleteBusiTestexercise(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }
}
