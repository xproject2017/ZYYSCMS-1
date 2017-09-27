package com.cms.core.manager.admin.core.system;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysStaff;
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
public class SysMenuManagerImp implements SysMenuManager {
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
    public TSysMenu getSysMenu(TSysMenu bean) {
        TSysMenu result =null;
        try {
            result = sysMenuDao.getSysMenu(bean);
            if (result == null) {
                result = new TSysMenu();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TSysMenu();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TSysMenu> getSysMenuList(TSysMenu bean) {
        List<TSysMenu> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysMenuDao.getSysMenuGroup(bean);
                setPageInfo(bean, map);
            }
            list = sysMenuDao.getSysMenuList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysMenu>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysMenu>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getSysMenuGroup(TSysMenu bean) {
        HashMap map =null;
        try{
            map = sysMenuDao.getSysMenuGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TSysMenu addSysMenu(TSysMenu bean) throws ManagerException{
        TSysMenu result =null;
            result = new TSysMenu();
            int cnt=sysMenuDao.addSysMenu(bean);
            result.setDbcnt(cnt);
            if(cnt!=1){
                throw new ManagerException(ResultInfo.ErrorDBOperation);
            }
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysMenu updateSysMenu(TSysMenu bean) {
        TSysMenu result =null;
            result = new TSysMenu();
            int cnt=sysMenuDao.updateSysMenu(bean);
            result.setDbcnt(cnt);
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysMenu deleteSysMenu(TSysMenu bean) {
        TSysMenu result =null;
            result = new TSysMenu();
            int cnt=sysMenuDao.deleteSysMenu(bean);
            result.setDbcnt(cnt);
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public List<TSysMenu> getSysMenuListByRoleids(TSysStaff bean) {
        List<TSysMenu> list=null;
        try {
            list = sysMenuDao.getSysMenuListByRoleids(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysMenu>();
                    TSysMenu res=new TSysMenu();
                    res.setResult(ResultInfo.NullOutput);
                    res.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(res);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            TSysMenu res=new TSysMenu();
            res.setResult(ResultInfo.Fail);
            res.setFailinfo(ResultInfo.ErrorQuery);
            list.add(res);
            return list;
        }
    }

    @Override
    public List<TSysMenu> getSysMenuListByUserid(TSysStaff bean) {
        List<TSysMenu> list=null;
        try {
            list = sysMenuDao.getSysMenuListByUserid(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysMenu>();
                    TSysMenu res=new TSysMenu();
                    res.setResult(ResultInfo.NullOutput);
                    res.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(res);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            TSysMenu res=new TSysMenu();
            res.setResult(ResultInfo.Fail);
            res.setFailinfo(ResultInfo.ErrorQuery);
            list.add(res);
            return list;
        }
    }
}
