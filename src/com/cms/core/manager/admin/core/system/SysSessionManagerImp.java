package com.cms.core.manager.admin.core.system;

import com.cms.core.bean.cms.core.TSysSession;
import com.cms.core.dao.system.SysSessionDao;
import com.cms.core.bean.BaseBean;
import com.cms.core.manager.ManagerException;
import com.cms.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;


@Service
@Transactional
public class SysSessionManagerImp implements SysSessionManager {

    // 60 minutes     30day
    static public final long SeesionTimeout = 60 * 60 * 24 * 30 ;
    // 30 minutes
    static public final long SessionUpdateInterval = 60 * 30;

    @Autowired
    private SysSessionDao sysSessionDao;



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
    public TSysSession getSysSession(TSysSession bean) {
        TSysSession result =null;
        try {
            result = sysSessionDao.getSysSession(bean);
            if (result == null) {
                result = new TSysSession();
                result.setResult(ResultInfo.NullOutput);
                result.setFailinfo(ResultInfo.ErrorNoDataFound);
                return result;
            }
            result.setResult(ResultInfo.Success);
            return result;
        }catch (Exception e){
            result = new TSysSession();
            result.setResult(ResultInfo.Fail);
            System.out.println(e.getMessage());
            result.setFailinfo(ResultInfo.ErrorQuery);
            return result;
        }
    }

    @Override
    public TSysSession addSysSession(TSysSession bean) throws ManagerException{
        TSysSession result =null;
            result = new TSysSession();
            int cnt=sysSessionDao.addSysSession(bean);
            result.setDbcnt(cnt);
            if(cnt!=1){
                throw new ManagerException(ResultInfo.ErrorDBOperation);
            }
            result.setUserid(bean.getUserid());
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysSession updateSysSession(TSysSession bean) {
        TSysSession result =null;
            result = new TSysSession();
            int cnt=sysSessionDao.updateSysSession(bean);
            result.setDbcnt(cnt);
            result.setUserid(bean.getUserid());
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysSession deleteSysSession(TSysSession bean) {
        TSysSession result =null;
            result = new TSysSession();
            int cnt=sysSessionDao.updateSysSession(bean);
            result.setDbcnt(cnt);
            result.setUserid(bean.getUserid());
            result.setResult(ResultInfo.Success);
            return result;
    }

    @Override
    public TSysSession checkSysSession(TSysSession bean) {

        //数据库中的格式：!token!局方id!平台id|用户id                  数据库中没有对应的流水号
        //session中的格式：token流水号!token!局方id!平台id|用户id      登陆的时候需要将流水号拼接进来
        //参见 SysFlagUtil.PLATFORM_*    0-8
        //参见  SysFlagUtil.org_*       局方id:1杭州,2上海,3平潭,4天津,5广州,6重庆,7宁波,8郑州,9深圳,10福州
        //1647!B77D14A6D35E7D38003A014DD2146C95!1!0!3

        Date dateNow = new Date(System.currentTimeMillis());

        //判断用户是否登录
        String token = bean.getSessiontoken();
        if (token == null || "".equals(token)) {
            bean.setResult(ResultInfo.ErrorToken);
            bean.setFailinfo(ResultInfo.ErrorTokenStr);
            return bean;
        }
        String[] tokenArray = token.split("!");
        if(tokenArray.length!=5){
            bean.setResult(ResultInfo.ErrorToken);
            bean.setFailinfo(ResultInfo.ErrorTokenStr);
            return bean;
        }
        Long session_sessionid=Long.valueOf(tokenArray[0]);
        String session_token=tokenArray[2];
        Integer session_orgid=Integer.valueOf(tokenArray[2]);  //暂时没有想到要怎么用z
        Integer session_plantform=Integer.valueOf(tokenArray[3]);//暂时没有想到要怎么用z
        Integer session_userid=Integer.valueOf(tokenArray[4]);

        TSysSession where=new TSysSession();
          where.setSessionid(session_sessionid);
        TSysSession dbuserSession = sysSessionDao.getSysSession(where);
        if (dbuserSession == null) {
            bean.setResult(ResultInfo.ErrorToken);
            bean.setFailinfo(ResultInfo.ErrorTokenStr);
            return bean;
        } else {
            //userid不一致
            if(!session_userid.equals(dbuserSession.getUserid())){
                bean.setResult(ResultInfo.ErrorToken);
                bean.setFailinfo(ResultInfo.ErrorTokenStr);
                return bean;
            }
            //token不一致
            if(!session_token.equals(dbuserSession.getSessiontoken().split("!")[2])){
                bean.setResult(ResultInfo.ErrorToken);
                bean.setFailinfo(ResultInfo.ErrorTokenStr);
                return bean;
            }

            //如果超时，返回超时信息
            long interval=(dateNow.getTime() - dbuserSession.getLasttime().getTime())/1000;

            if (interval >=  SeesionTimeout) {
                //超时了不用逐条清除token，启用db清理维护进程，定时清理。
                //sessionDao.deleteUserSession(userSession);
                bean.setResult(ResultInfo.ErrorTokenTimeout);
                bean.setFailinfo(ResultInfo.ErrorTokenTimeoutStr);
                return bean;
            }
            if(interval >=  SessionUpdateInterval) {
                //更新session的最近活跃时间
                dbuserSession.setLasttime(dateNow);
                sysSessionDao.updateSysSession(dbuserSession);
            }
            dbuserSession.setResult(ResultInfo.Success);
            return dbuserSession;
        }
    }
}
