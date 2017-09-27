package com.cms.core.auth;

import com.cms.core.bean.cms.core.TSysSession;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.system.SysSessionManager;
import com.cms.util.Constants;
import com.cms.util.ResultInfo;
import com.cms.util.ServeletIPUtil;
import com.cms.util.SysFlagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class TWebSecurityContext  extends HandlerInterceptorAdapter {
    @Autowired
    public SysSessionManager sysSessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (ex instanceof HttpRequestMethodNotSupportedException) {
//            request.getRequestDispatcher("/timeout.jsp").forward(request, response);
//            return false;
//        }

        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String ip = request.getRemoteAddr();//返回发出请求的IP地址
        String params = request.getQueryString();//返回请求行中的参数部分
        String host = request.getRemoteHost();//返回发出请求的客户机的主机名
        int port = request.getRemotePort();//返回发出请求的客户机的端口号。
//        System.out.println(ip);
//        System.out.println(url);
//        System.out.println(uri);
//        System.out.println(params);
//        System.out.println(host);
//        System.out.println(port);

        ip = ServeletIPUtil.getIpAddress(request);
        //System.out.println("ip==" + ip);
        ip = ServeletIPUtil.getIpAddress4Proxy(request);
        //System.out.println("ip==" + ip);
        ip = ServeletIPUtil.getLocalIp(request);
        //System.out.println("ip==" + ip);
        ip = ServeletIPUtil.getIp(request);
        //System.out.println("ip==" + ip);
        request.setCharacterEncoding("UTF-8");
        url = request.getServletPath();     //request.getContextPath()
        String path = request.getPathInfo();
        String token = request.getHeader(SysFlagUtil.CATOKEN_Attribute);
        if (token == null) {
            token = request.getParameter(SysFlagUtil.CATOKEN_Attribute);
        }

        //System.out.println("url:" + url + " token:" + token + " path:" + path);
        if (donotCheckToken(url)) {

            if (url.contains("/view/admin/")) {
                TSysStaff usersBean = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
                if (usersBean == null) {
                    request.getRequestDispatcher("/timeout.jsp").forward(request, response);
                    return false;
                }
            }
            return true;
        } else {
            if (token != null) {
                String[] tokenArray = token.split("!");
                //数据库中的格式：0!token!局方id!平台id|用户id
                //session中的格式：token流水号!token!局方id!平台id|用户id
                //参见 SysFlagUtil.PLATFORM_*    0-8
                //参见  SysFlagUtil.org_*       局方id:1杭州,2上海,3平潭,4天津,5广州,6重庆,7宁波,8郑州,9深圳,10福州
                //1647!B77D14A6D35E7D38003A014DD2146C95!1!0!3
                if (tokenArray.length != 5) {
                    //无效的token
                    request.getRequestDispatcher("/timeout.jsp").forward(request, response);
                    return false;
                } else {
                    TSysSession stoken = new TSysSession();
                    stoken.setToken(token);
                    TSysSession checksession = sysSessionManager.checkSysSession(stoken);
                    if (checksession.getResult() == ResultInfo.Success) {
                        response.setHeader(SysFlagUtil.CATOKEN_Attribute, checksession.getToken());
                        return true;
                    } else {
                        //无效的token
                        request.getRequestDispatcher("/timeout.jsp").forward(request, response);
                        return false;
                    }
                }
            } else {
                //无效的token
                request.getRequestDispatcher("/timeout.jsp").forward(request, response);
                return false;
            }

        }

    }

    private boolean donotCheckToken(String url){
        //url:/css/style.css token:null path:null
        //url:/js/jquery-1.11.1.js token:null path:null
        if(url.contains("/thirdapi/")||url.contains("/thirdscreen/")||url.contains("/auth/")||url.contains("/view/")||url.contains("/image/")||url.contains("/css/")||url.contains("/js/")||url.contains("/kindeditor/")||url.contains("/sass/")||url.contains("/testapi/")||url.contains("/util/")){
            return true;
        }
        //http://localhost:8088/get_message_page.action?type=1
        //url:/get_message_page.action

        StringBuilder sb=new StringBuilder();
        //无需token
        sb.append("#/test.action");
        sb.append("#/registeappuserapi.action");


        sb.append("#");
        return sb.toString().contains(url);
    }
}
