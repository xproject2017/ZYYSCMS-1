package com.cms.core.controller.cmsui;

import com.cms.core.bean.cms.busi.*;
import com.cms.core.bean.cms.core.*;
import com.cms.core.manager.admin.core.foundcms.*;
import com.cms.util.DateUtil;
import com.cms.util.ResourceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * 登录
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms")
public class InfoController {

    @Autowired
    private BusiPmultiplemsgManager busiPmultiplemsgManager;//(1,2,3,4,14)  (1,2,14表示单页面)

    @Autowired
    private BusiPnesManager busiPnesManager;//(5,7,8)

    @Autowired
    private BusiPnoticeManager busiPnoticeManager;//(6公司公告)

    @Autowired
    private BusiPproductManager busiPproductManager;//(9,11)

    @Autowired
    private BusiPquestionManager busiPquestionManager;//(12)

    @Autowired
    private BusiPconsultationManager busiPconsultationManager;//(13)

    @Autowired
    private BusiPbespokeManager busiPbespokeManager;//(10)

    @Autowired
    private BusiRecruitManager busiRecruitManager;//(15)

    @Autowired
    private BusiApplyManager busiApplyManager;//应聘

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getMulptListData")
    @ResponseBody
    public Map<String,Object> getMulptListData(MenuBean menu){
        HashMap<String, Object> result = new HashMap<>();
        if(menu.getNavigationid2()==3 || menu.getNavigationid2()==4){
            PmultiplemsgBean bean = new PmultiplemsgBean();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPageFlag(1);
            List<TBusiPmultiplemsg>	 msg = busiPmultiplemsgManager.getBusiPmultiplemsgList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }else if(menu.getNavigationid2()==5 || menu.getNavigationid2()==7 || menu.getNavigationid2()==8){
            PnesBean bean = new PnesBean();
            bean.setPageFlag(1);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            List<TBusiPnes>	msg = busiPnesManager.getBusiPnesList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }else if(menu.getNavigationid2()==6){
            TBusiPnotice bean = new TBusiPnotice();
            bean.setPageFlag(1);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            List<TBusiPnotice>	msg = busiPnoticeManager.getBusiPnoticeList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }else if(menu.getNavigationid2()==9 || menu.getNavigationid2()==11){
            TBusiPproduct bean = new TBusiPproduct();
            bean.setPageFlag(1);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            List<TBusiPproduct>	msg = busiPproductManager.getBusiPproductList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }else if(menu.getNavigationid2()==12){
            TBusiPquestion bean = new TBusiPquestion();
            bean.setPageFlag(1);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            List<TBusiPquestion> msg = busiPquestionManager.getBusiPquestionList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }else if(menu.getNavigationid2()==15){
            TBusiRecruit bean = new TBusiRecruit();
            bean.setPageFlag(1);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            List<TBusiRecruit> msg = busiRecruitManager.getBusiRecruitList(bean);
            result.put("result", msg);
            result.put("code", msg.get(0).getResult());
            result.put("failinfo", msg.get(0).getFailinfo());
        }
        return result;
    }



    @RequestMapping("/fwsimple")
    @ResponseBody
    public ModelAndView forwardMenuPage(HttpServletRequest request,MenuBean menu){
        Map<Integer,List<MenuBean>> map = (Map<Integer, List<MenuBean>>) request.getSession().getAttribute("navMenuList");
        List<MenuBean> list = map.get(menu.getNavigationid1());

        for(MenuBean m : list){
            if(m.getNavigationid2().equals(menu.getNavigationid2())){
                menu.setNodetext(m.getNodetext());
                break;
            }
        }

        if(menu.getNavigationid1()==2 && menu.getNavigationid2()==0){
            menu.setNavigationid2(1);
            menu.setNodetext(list.get(1).getNodetext());
        }else if(menu.getNavigationid1()==3 && menu.getNavigationid2()==0){
            menu.setNavigationid2(5);
            menu.setNodetext(list.get(1).getNodetext());
        }else if(menu.getNavigationid1()==4 && menu.getNavigationid2()==0){
            menu.setNavigationid2(9);
            menu.setNodetext(list.get(1).getNodetext());
        }else if(menu.getNavigationid1()==5 && menu.getNavigationid2()==0){
            menu.setNavigationid2(14);
            menu.setNodetext(list.get(1).getNodetext());
        }
        ModelAndView view = new ModelAndView("/mulpt");
        if(menu.getNavigationid2()==1 || menu.getNavigationid2()==2 || menu.getNavigationid2()==14){
            view = new ModelAndView("/simple");
            PmultiplemsgBean bean = new PmultiplemsgBean();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            TBusiPmultiplemsg msg = busiPmultiplemsgManager.getBusiPmultiplemsg(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==13){
            view = new ModelAndView("/pconsultation");
        }else if(menu.getNavigationid2()==10){
            view = new ModelAndView("/pbespoke");
        }
        view.addObject("nodetext",menu.getNodetext());
        view.addObject("paramobj",menu);
        view.addObject("leftmenu",list);
        return view;
    }

    @RequestMapping("/fwcontext")
    @ResponseBody
    public ModelAndView fwcontext(HttpServletRequest request,MenuBean menu,Integer mesgid){
        Map<Integer,List<MenuBean>> map = (Map<Integer, List<MenuBean>>) request.getSession().getAttribute("navMenuList");
        List<MenuBean> list = map.get(menu.getNavigationid1());
        ModelAndView view = new ModelAndView("/context");

        for(MenuBean m : list){
            if(m.getNavigationid2().equals(menu.getNavigationid2())){
                menu.setNodetext(m.getNodetext());
                break;
            }
        }

        if(menu.getNavigationid2()==1 || menu.getNavigationid2()==2 || menu.getNavigationid2()==14){
            view = new ModelAndView("/simple");
            PmultiplemsgBean bean = new PmultiplemsgBean();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiPmultiplemsg msg = busiPmultiplemsgManager.getBusiPmultiplemsg(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==3 || menu.getNavigationid2()==4){
            PmultiplemsgBean bean = new PmultiplemsgBean();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setMesgid(mesgid);
            bean.setPrev_pageflag(1);
            TBusiPmultiplemsg msg = busiPmultiplemsgManager.getBusiPmultiplemsg(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==5 || menu.getNavigationid2()==7 || menu.getNavigationid2()==8){
            TBusiPnes bean = new TBusiPnes();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setMesgid(mesgid);
            bean.setPrev_pageflag(1);
            TBusiPnes msg = busiPnesManager.getBusiPnes(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==6){
            TBusiPnotice bean = new TBusiPnotice();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setMesgid(mesgid);
            bean.setPrev_pageflag(1);
            TBusiPnotice msg = busiPnoticeManager.getBusiPnotice(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==9 || menu.getNavigationid2()==11){
            TBusiPproduct bean = new TBusiPproduct();
            bean.setMesgid(mesgid);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiPproduct msg = busiPproductManager.getBusiPproduct(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==12){
            TBusiPquestion bean = new TBusiPquestion();
            bean.setMesgid(mesgid);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiPquestion msg = busiPquestionManager.getBusiPquestion(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==13){
            view = new ModelAndView("/consultationcontext");
            TBusiPconsultation bean = new TBusiPconsultation();
            bean.setConsid(mesgid);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiPconsultation msg = busiPconsultationManager.getBusiPconsultation(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data", msg);
        }else if(menu.getNavigationid2()==10){
            view = new ModelAndView("/bespokecontext");
            TBusiPbespoke bean = new TBusiPbespoke();
            bean.setBespokeid(mesgid);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiPbespoke msg = busiPbespokeManager.getBusiPbespoke(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }else if(menu.getNavigationid2()==15){
            TBusiRecruit bean = new TBusiRecruit();
            bean.setMesgid(mesgid);
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setPrev_pageflag(1);
            TBusiRecruit msg = busiRecruitManager.getBusiRecruit(bean);
            view.addObject("code",msg.getResult());
            view.addObject("data",msg);
        }
        view.addObject("nodetext",menu.getNodetext());
        view.addObject("paramobj",menu);
        view.addObject("leftmenu",list);
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getBespkeListData")
    @ResponseBody
    public Map<String,Object> getBespkeListData(PbespokeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPbespoke>	list = busiPbespokeManager.getBusiPbespokeList(bean);
        int code = list.get(0).getResult();
        if(code == 0){
            List<TBusiPbespoke> busiPbespokeList = new ArrayList<>();
            for(TBusiPbespoke bespoke : list){
                PbespokeBean pbespokeBean = new PbespokeBean();
                BeanUtils.copyProperties(bespoke,pbespokeBean);
                pbespokeBean.setPbespoketimestr(DateUtil.convertDate2String(bespoke.getPbespoketime(), DateUtil.DEFAULT_DATE_FORMAT));
                pbespokeBean.setFlagstr(bespoke.getFlag()==0?"未处理":"已处理");
                busiPbespokeList.add(pbespokeBean);
            }
            result.put("result", busiPbespokeList);
        }
        result.put("code", code);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getConsultationListData")
    @ResponseBody
    public Map<String,Object> getConsultationListData(PconsultationBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPconsultation>	list = busiPconsultationManager.getBusiPconsultationList(bean);
        int code = list.get(0).getResult();
        if(code == 0){
            List<TBusiPconsultation> busiPbespokeList = new ArrayList<>();
            for(TBusiPconsultation bespoke : list){
                PconsultationBean PconsultationBean = new PconsultationBean();
                BeanUtils.copyProperties(bespoke,PconsultationBean);
                PconsultationBean.setConstimestr(DateUtil.convertDate2String(bespoke.getConstime(), DateUtil.DEFAULT_DATE_FORMAT));
                PconsultationBean.setFlagstr(bespoke.getFlag()==0?"未处理":"已处理");
                busiPbespokeList.add(PconsultationBean);
            }
            result.put("result", busiPbespokeList);
        }
        result.put("code", code);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }


    @RequestMapping(value = "/addApply")
    @ResponseBody
    public Object addApply(@ModelAttribute ApplyBean bean){
        bean.setApplytime(new Date());
        bean.setFlag(0);
        bean.setResmes("");
        Map<String,Object> result = new HashMap<>();
        try {
            TBusiApply apply = busiApplyManager.addBusiApply(bean);
            if(apply.getResult() == 0){
                String path =  ResourceUtil.getSystem("image.upload.path") + "apply" + File.separator + apply.getId();
                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdirs();
                File f = new File(path + File.separator + bean.getApplyfile().getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(f);
                FileCopyUtils.copy(bean.getApplyfile().getBytes(), fos);
                result.put("code",apply.getResult());
                result.put("failinfo", apply.getFailinfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
