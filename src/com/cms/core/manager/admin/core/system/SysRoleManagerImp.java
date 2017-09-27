package com.cms.core.manager.admin.core.system;

import com.cms.core.bean.cms.core.RoleMenus;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.dao.system.SysRoleDao;
import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TSysRolemenus;
import com.cms.core.dao.system.SysRolemenusDao;
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
public class SysRoleManagerImp implements SysRoleManager {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRolemenusDao sysRolemenusDao;

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
    public TSysRole getSysRole(TSysRole bean) {
        TSysRole result =null;
        try {
            result = sysRoleDao.getSysRole(bean);
            if (result == null) {
                result = new TSysRole();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TSysRole();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TSysRole> getSysRoleList(TSysRole bean) {
        List<TSysRole> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysRoleDao.getSysRoleGroup(bean);
                setPageInfo(bean, map);
            }
            list = sysRoleDao.getSysRoleList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysRole>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysRole>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getSysRoleGroup(TSysRole bean) {
        HashMap map =null;
        try{
            map = sysRoleDao.getSysRoleGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TSysRole addSysRole(TSysRole bean) throws ManagerException{
        TSysRole result =null;
            result = new TSysRole();
            int cnt=sysRoleDao.addSysRole(bean);
            result.setDbcnt(cnt);
            if(cnt!=1){
                throw new ManagerException(ResultInfo.ErrorDBOperation);
            }
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysRole updateSysRole(TSysRole bean) {
        TSysRole result =null;
            result = new TSysRole();
            int cnt=sysRoleDao.updateSysRole(bean);
            result.setDbcnt(cnt);
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysRole deleteSysRole(TSysRole bean) {
        TSysRole result =null;
            result = new TSysRole();
            int cnt=sysRoleDao.deleteSysRole(bean);
            result.setDbcnt(cnt);
            result.setResult(ResultInfo.Success);
            return result;
    }


    @Override
    public TSysRolemenus getSysRolemenus(TSysRolemenus bean) {
        TSysRolemenus result =null;
        try {
            result = sysRolemenusDao.getSysRolemenus(bean);
            if (result == null) {
                result = new TSysRolemenus();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TSysRolemenus();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TSysRolemenus> getSysRolemenusList(TSysRolemenus bean) {
        List<TSysRolemenus> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysRolemenusDao.getSysRolemenusGroup(bean);
                setPageInfo(bean, map);
            }
            list = sysRolemenusDao.getSysRolemenusList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysRolemenus>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysRolemenus>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getSysRolemenusGroup(TSysRolemenus bean) {
        HashMap map =null;
        try{
            map = sysRolemenusDao.getSysRolemenusGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TSysRolemenus addSysRolemenus(TSysRolemenus bean) throws ManagerException{
        TSysRolemenus result =null;
        result = new TSysRolemenus();
        int cnt=sysRolemenusDao.addSysRolemenus(bean);
        result.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TSysRolemenus updateSysRolemenus(TSysRolemenus bean) {
        TSysRolemenus result =null;
        result = new TSysRolemenus();
        int cnt=sysRolemenusDao.updateSysRolemenus(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TSysRolemenus deleteSysRolemenus(TSysRolemenus bean) {
        TSysRolemenus result =null;
        result = new TSysRolemenus();
        int cnt=sysRolemenusDao.deleteSysRolemenus(bean);
        result.setDbcnt(cnt);
        result.setResult(ResultInfo.Success);
        return result;
    }

    @Override
    public TSysRole addRoleAndRoleMenus(RoleMenus bean) {
        int cnt=sysRoleDao.addSysRole(bean);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        for(Integer nodeid:bean.getMenuidList())  {
            TSysRolemenus tmp=new TSysRolemenus();
            tmp.setRoleid(bean.getRoleid());
            tmp.setNodeid(nodeid);
            //int i=1/0;
            cnt=sysRolemenusDao.addSysRolemenus(tmp);
            if(cnt!=1){
                throw new ManagerException(ResultInfo.ErrorDBOperation);
            }
        }
        bean.setDbcnt(cnt);
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public TSysRole updateRoleAndRoleMenus(RoleMenus bean) {
        int cnt=sysRoleDao.updateSysRole(bean);
        TSysRolemenus tmp=new TSysRolemenus();
        tmp.setRoleid(bean.getRoleid());
        sysRolemenusDao.deleteSysRolemenus(tmp);
        //String[] nodeids=bean.getMenuids().split(",");
        for(Integer nodeid:bean.getMenuidList())  {
            tmp=new TSysRolemenus();
            tmp.setRoleid(bean.getRoleid());
            tmp.setNodeid(nodeid);
            cnt=sysRolemenusDao.addSysRolemenus(tmp);
            if(cnt!=1){
                throw new ManagerException(ResultInfo.ErrorDBOperation);
            }
        }
        bean.setDbcnt(cnt);
        bean.setResult(ResultInfo.Success);
        return bean;
    }

    @Override
    public List<TSysRole> getRoleListPriority(TSysRole bean) {
        List<TSysRole> list=null;
        try {
            list = sysRoleDao.getSysRoleList_gt_rid(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysRole>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysRole>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }
}
