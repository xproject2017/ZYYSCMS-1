package com.cms.core.controller.admin;

import com.cms.core.bean.cms.core.TSysSession;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.util.Constants;
import com.cms.util.ResultInfo;
import com.cms.util.SysFlagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class LoginController {

    @Autowired
    private AccountManager usersManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "redirect:adminIndex.jsp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@ModelAttribute TSysStaff users,HttpServletRequest request,HttpServletResponse response){
        TSysStaff tSysStaff = usersManager.getAccountDetail(users);
        Map<String,Object> result = new HashMap<>();
        if(tSysStaff.getResult()==0){
            if (tSysStaff.getUstatus() == SysFlagUtil.USER_STAUTS_DISABLE){
                result.put("code", ResultInfo.Fail);
                result.put("failinfo", ResultInfo.USER_DISABLE_ERROR);
                return result;
            }
            if(tSysStaff.getUserpassword().equals(users.getUserpassword())){
//                if(tSysStaff.getRoleids().equals("4"))
//                    tSysStaff.setRoleid(2);
//                else
//                    tSysStaff.setRoleid(1);

                //写token
                TSysSession syssesion=usersManager.writeToken(tSysStaff);
                response.setHeader(SysFlagUtil.CATOKEN_Attribute,syssesion.getSessiontoken());// //将token写到head中
                request.getSession().setAttribute(SysFlagUtil.CATOKEN_Attribute,syssesion.getSessiontoken()); //将token写到session中

                request.getSession().setAttribute(Constants.USER_SESSION_INFO,tSysStaff);
                result.put("code", ResultInfo.Success);
                result.put("roleid", tSysStaff.getRoleid());
            }else {
                result.put("code",ResultInfo.NullOutput);
                result.put("failinfo", ResultInfo.USER_PWD_ERROR);
            }
        }else {
            result.put("code",ResultInfo.NullOutput);
            result.put("failinfo", ResultInfo.USER_PWD_ERROR);
        }

        return result;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request){
        request.getSession().removeAttribute(Constants.USER_SESSION_INFO);
    }
}
