package com.cms.core.manager.admin.core.system;

import com.cms.core.bean.BaseBean;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.dao.system.*;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysStaffManagerImp implements SysStaffManager {
    @Autowired
    public SysStaffDao sysStaffDao;
    @Autowired
    public SysMenuDao sysMenuDao;
    @Autowired
    private SysRolemenusDao sysRolemenusDao;
    @Autowired
    private SysRoleDao sysRoleDao;


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
    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
    public TSysStaff getSysStaff(TSysStaff bean) {
        TSysStaff result =null;
        try {
            //sysStaffDao.addSysStaff(bean);
            result = sysStaffDao.getSysStaff(bean);
            if (result == null) {
                result = new TSysStaff();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TSysStaff();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public List<TSysStaff> getSysStaffList(TSysStaff bean) {
        List<TSysStaff> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysStaffDao.getSysStaffGroup(bean);
                setPageInfo(bean, map);
            }
            list = sysStaffDao.getSysStaffList(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysStaff>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysStaff>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public HashMap getSysStaffGroup(TSysStaff bean) {
        HashMap map =null;
        try{
            map = sysStaffDao.getSysStaffGroup(bean);
            return map;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return map;
        }
    }

    @Override
    public TSysStaff addSysStaff(TSysStaff bean) {
        System.out.println("bean.getUsername()==manager==1="+bean.getUname());
        TSysStaff result =null;
        result = new TSysStaff();
        int cnt=sysStaffDao.addSysStaff(bean);
        result.setDbcnt(cnt);
        if(cnt!=1){
            throw new ManagerException(ResultInfo.ErrorDBOperation);
        }
        result.setUserid(bean.getUserid());
        result.setResult(ResultInfo.Success);
//        TSysTips tip = new TSysTips(bean);
//        sysTipsDao.addSysTips(tip);
        return result;
    }

    @Override
    public TSysStaff updateSysStaff(TSysStaff bean) {
        TSysStaff result = new TSysStaff();
            int cnt=sysStaffDao.updateSysStaff(bean);
            result.setDbcnt(cnt);
            result.setUserid(bean.getUserid());
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysStaff deleteSysStaff(TSysStaff bean) {
        TSysStaff result =null;
            result = new TSysStaff();
            int cnt=sysStaffDao.deleteSysStaff(bean);
            result.setDbcnt(cnt);
            result.setUserid(bean.getUserid());
            result.setResult(ResultInfo.Success);
            return result;
    }


    @Override
    public List<TSysStaff> getSysStaffListByRoleName(TSysRole bean) {

        List<TSysStaff> list=null;
        TSysStaff where = new TSysStaff();
        try {
            where.setRoleid(sysRoleDao.getSysRole(bean).getRoleid());
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysStaffDao.getSysStaffGroup(where);
                setPageInfo(bean, map);
            }
            list = sysStaffDao.getSysStaffList(where); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysStaff>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(where);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysStaff>();
            where.setResult(ResultInfo.Fail);
            where.setFailinfo(ResultInfo.ErrorQuery);
            list.add(where);
            return list;
        }
    }

    @Override
    public List<TSysStaff> getSysStaffListByUName(TSysStaff bean) {

        List<TSysStaff> list=null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysStaffDao.getSysStaffGroupByUName(bean);
                setPageInfo(bean, map);
            }
            list = sysStaffDao.getSysStaffListByUName(bean); //查询不到数据返回null
            if(list!=null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                }else {
                    list=new ArrayList<TSysStaff>();
                    bean.setResult(ResultInfo.NullOutput);
                    bean.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(bean);
                }
            }
            return list;
        }catch (Exception e){
            System.out.println(e.getMessage());
            list=new ArrayList<TSysStaff>();
            bean.setResult(ResultInfo.Fail);
            bean.setFailinfo(ResultInfo.ErrorQuery);
            list.add(bean);
            return list;
        }
    }

    @Override
    public List<TSysStaff> getSysStaffListByMenu4Ctl(TSysMenu bean) {
        List<TSysStaff> list = null;
        try {
            if (bean.getPageFlag() != null && bean.getPageFlag() == 1) {//分页
                HashMap map = sysStaffDao.getSysStaffListByMenu4Ctlgroup(bean);
                setPageInfo(bean, map);
            }
            list = sysStaffDao.getSysStaffListByMenu4Ctl(bean); //查询不到数据返回null
            if (list != null) {
                if (list.size() > 0) {
                    list.get(0).setResult(ResultInfo.Success);
                    list.get(0).setCurrentPage(bean.getCurrentPage());
                    list.get(0).setPageSize(bean.getPageSize());
                    list.get(0).setTotalNum(bean.getTotalNum());
                } else {
                    TSysStaff staff = new TSysStaff();
                    list = new ArrayList<TSysStaff>();
                    staff.setResult(ResultInfo.NullOutput);
                    staff.setFailinfo(ResultInfo.ErrorNoDataFound);
                    list.add(staff);
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            TSysStaff staff = new TSysStaff();
            list = new ArrayList<TSysStaff>();
            staff.setResult(ResultInfo.Fail);
            staff.setFailinfo(ResultInfo.ErrorQuery);
            list.add(staff);
            return list;
        }
    }


}
