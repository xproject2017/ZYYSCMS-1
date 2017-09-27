package com.cms.core.controller.admin;

import com.cms.core.bean.cms.core.TSysSession;
import com.cms.core.manager.admin.core.system.SysSessionManager;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.util.ResultInfo;
import com.cms.util.SysFlagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
@Controller
@Scope("prototype")
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private AccountManager usersManager;
    @Autowired
    public SysSessionManager sysSessionManager;

    @RequestMapping(value = "/checkTokenapi", method = RequestMethod.GET)
    @ResponseBody
    public Object checkTokenapi(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/plain;charset=utf8");

        Map<String,Object> result = new HashMap<>();
        String token=request.getHeader(SysFlagUtil.CATOKEN_Attribute);

        if(token==null){
            //String res ="{\"Result\":"+ ResultInfo.Fail+",\"FailInfo\":\""+ResultInfo.ErrorParameter+"\"}" ;
            result.put("Result",ResultInfo.Fail);
            result.put("ResultInfo",ResultInfo.ErrorParameter);
            return result;
        }
        String[] tokenArray = token.split("!");
        //数据库中的格式：0!token!局方id!平台id|用户id
        //session中的格式：token流水号!token!局方id!平台id|用户id
        //参见 SysFlagUtil.PLATFORM_*    0-8
        //参见  SysFlagUtil.org_*       局方id:1杭州,2上海,3平潭,4天津,5广州,6重庆,7宁波,8郑州,9深圳,10福州
        //1647!B77D14A6D35E7D38003A014DD2146C95!1!0!3
        if (tokenArray.length != 5) {
            //无效的token
            //String res ="{\"Result\":"+ ResultInfo.Fail+",\"FailInfo\":\""+ResultInfo.ErrorParameter+"\"}" ;
            result.put("Result",ResultInfo.Fail);
            result.put("ResultInfo",ResultInfo.ErrorParameter);
            return result;
        } else {
            TSysSession stoken=new TSysSession();
            stoken.setToken(token);
            TSysSession checksession=sysSessionManager.checkSysSession(stoken);
            if(checksession.getResult()== ResultInfo.Success){
                response.setHeader(SysFlagUtil.CATOKEN_Attribute,checksession.getToken());
                //String res ="{\"Result\":"+ ResultInfo.Success+"}" ;
                result.put("Result",ResultInfo.Success);
                return result;
            } else {
                //无效的token
                //String res ="{\"Result\":"+ checksession.getResult()+",\"FailInfo\":\""+checksession.getFailinfo()+"\"}" ;
                result.put("Result",checksession.getResult());
                result.put("ResultInfo",checksession.getFailinfo());
                return result;
            }
        }
    }
}
