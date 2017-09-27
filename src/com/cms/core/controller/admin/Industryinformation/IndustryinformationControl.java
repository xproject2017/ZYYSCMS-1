package com.cms.core.controller.admin.Industryinformation;

import com.cms.core.bean.cms.busi.IndustryinformationBean;
import com.cms.core.bean.cms.busi.NationalBean;
import com.cms.core.bean.cms.busi.PproductBean;
import com.cms.core.bean.cms.core.TBusiIndustryinformation;
import com.cms.core.bean.cms.core.TBusiPproduct;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiIndustryinformationManager;
import com.cms.util.Constants;
import com.cms.util.DateUtil;
import com.cms.util.ResourceUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2017/9/14.
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/industry")
public class IndustryinformationControl {

    @Autowired
    private BusiIndustryinformationManager busiIndustryinformationManager;

    /**
     * 获取公司新闻列表页面
     * @return 页面
     */
    @RequestMapping("/getIndustrynewsListPage")
    public ModelAndView getIndustrynewsListPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/industrynews/list");
/*        List<TBusiIndustryinformation> industryinformationList = getIndustryinformationList();
        view.addObject("industryinformationList", industryinformationList);*/
        view.addObject("bean", bean);
        return view;
    }

    /**
     * 获取公司新闻列表页面
     * @return 页面
     */
    @RequestMapping("/getIndustrytendencyListPage")
    public ModelAndView getIndustrytendencyListPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/industrytendency/list");
/*        List<TBusiIndustryinformation> industryinformationList = getIndustryinformationList();
        view.addObject("industryinformationList", industryinformationList);*/
        view.addObject("bean", bean);
        return view;
    }

    /**
     * 查询行业新闻的数据
     * @param bean
     * @return
     */
    @RequestMapping("/getIndustrynewsListData")
    @ResponseBody
    public Map<String,Object> getIndustrynewsListData(IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setMtype(1);//行业新闻
        bean.setPageFlag(1);
        List<TBusiIndustryinformation>	list = busiIndustryinformationManager.getBusiIndustryinformationList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addnewsData")
    @ResponseBody
    public  Map<String,Object>  addNewsData(HttpServletRequest request, IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiIndustryinformation tBusiIndustryinformation = busiIndustryinformationManager.addBusiIndustryinformation(bean);
        if(tBusiIndustryinformation.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiIndustryinformation.getNavigationid1()+ File.separator+tBusiIndustryinformation.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiIndustryinformation.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiIndustryinformation.getResult());
        result.put("failinfo", tBusiIndustryinformation.getFailinfo());
        return result;
    }

    @RequestMapping("/addtendencyData")
    @ResponseBody
    public  Map<String,Object>  addTendencyData(HttpServletRequest request, IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiIndustryinformation tBusiIndustryinformation = busiIndustryinformationManager.addBusiIndustryinformation(bean);
        if(tBusiIndustryinformation.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiIndustryinformation.getNavigationid1()+ File.separator+tBusiIndustryinformation.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiIndustryinformation.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiIndustryinformation.getResult());
        result.put("failinfo", tBusiIndustryinformation.getFailinfo());
        return result;
    }


    @RequestMapping("/getIndustrytendencyListData")
    @ResponseBody
    public Map<String,Object> getIndustrytendencyListData(IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setMtype(2);//行业新闻
        bean.setPageFlag(1);
        List<TBusiIndustryinformation>	list = busiIndustryinformationManager.getBusiIndustryinformationList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addIndustrynewsPage")
    public ModelAndView addIndustrynewsPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/industrynews/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addIndustrytendencyPage")
    public ModelAndView addIndustrytendencyPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/industrytendency/add");
        view.addObject("bean", bean);
        return view;
    }

    private List<TBusiIndustryinformation> getIndustryinformationList(){
        TBusiIndustryinformation industry = new TBusiIndustryinformation();
        industry.setPageFlag(0);
        industry.setMtype(1);
        return busiIndustryinformationManager.getBusiIndustryinformationList(industry);
    }

    @RequestMapping("/updateIndustrynewsPage")
    public ModelAndView updateIndustrynewsPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/industrynews/update");
        TBusiIndustryinformation industry = busiIndustryinformationManager.getBusiIndustryinformation(bean);
        BeanUtils.copyProperties(industry,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(industry.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiIndustryinformation industry = busiIndustryinformationManager.updateBusiIndustryinformation(bean);
        MultipartFile file = bean.getFileimage();
        if(industry.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + bean.getNavigationid1()+File.separator+bean.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + bean.getMesgid() + ".jpg");
                if(fos.exists()){
                    fos.delete();
                }
                BufferedImage bIMG = ImageIO.read(file.getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", industry.getResult());
        result.put("failinfo", industry.getFailinfo());
        return result;
    }

    @RequestMapping("/updateIndustrytendencyPage")
    public ModelAndView updateIndustrytendencyPage(IndustryinformationBean bean){
        ModelAndView view = new ModelAndView("/admin/ndustrytendency/update");
        TBusiIndustryinformation industry = busiIndustryinformationManager.getBusiIndustryinformation(bean);
        BeanUtils.copyProperties(industry,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(industry.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(IndustryinformationBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiIndustryinformation industry = busiIndustryinformationManager.deleteBusiIndustryinformation(bean);
        result.put("code", industry.getResult());
        result.put("failinfo", industry.getFailinfo());
        return result;
    }



    /*@RequestMapping("/addProductPage")
    public ModelAndView addProductPage(PproductBean bean){
        ModelAndView view = new ModelAndView("/admin/pproduct/add");
        view.addObject("bean", bean);
        return view;
    }



    *//**
     * 获取国检账户列表数据
     * @return
     *//*
    @RequestMapping("/getProductListData")
    @ResponseBody
    public Map<String,Object> getProductListData(PproductBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiPproduct>	list = busiPproductManager.getBusiPproductList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request, PproductBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPproduct tBusiPproduct = busiPproductManager.addBusiPproduct(bean);
        if(tBusiPproduct.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPproduct.getNavigationid1()+ File.separator+tBusiPproduct.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPproduct.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPproduct.getResult());
        result.put("failinfo", tBusiPproduct.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PproductBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPproduct tBusiPproduct = busiPproductManager.updateBusiPproduct(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPproduct.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + bean.getNavigationid1()+File.separator+bean.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + bean.getMesgid() + ".jpg");
                if(fos.exists()){
                    fos.delete();
                }
                BufferedImage bIMG = ImageIO.read(file.getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPproduct.getResult());
        result.put("failinfo", tBusiPproduct.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PproductBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiPproduct tBusiPproduct = busiPproductManager.deleteBusiPproduct(bean);
        result.put("code", tBusiPproduct.getResult());
        result.put("failinfo", tBusiPproduct.getFailinfo());
        return result;
    }*/



}
