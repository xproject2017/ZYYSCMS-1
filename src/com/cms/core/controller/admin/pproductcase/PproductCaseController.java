package com.cms.core.controller.admin.pproductcase;

import com.cms.core.bean.cms.busi.PproductBean;
import com.cms.core.bean.cms.busi.PproductcaseBean;
import com.cms.core.bean.cms.core.TBusiPproduct;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPproductCaseManager;
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
 * Created by 浩 on 2017/9/6.
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pproductcase")
public class PproductCaseController {

    @Autowired
    private BusiPproductCaseManager busiPproductCaseManager;


    @RequestMapping("/getProductcaseListPage")
    public ModelAndView getProductcaseListPage(PproductcaseBean bean){
        ModelAndView view = new ModelAndView("/admin/pproductcase/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addProductcasePage")
    public ModelAndView addProductcasePage(PproductcaseBean bean){
        ModelAndView view = new ModelAndView("/admin/pproductcase/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateProductPage")
    public ModelAndView updateProductPage(PproductcaseBean bean){
        ModelAndView view = new ModelAndView("/admin/pproductcase/update");
        TBusiPproductcase tBusiPproductcase = busiPproductCaseManager.getBusiPproductCase(bean);
        BeanUtils.copyProperties(tBusiPproductcase,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPproductcase.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    @RequestMapping("/getProductcaseListData")
    @ResponseBody
    public Map<String,Object> getProductListData(PproductcaseBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiPproductcase>	list = busiPproductCaseManager.getBusiPproductCaseList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,PproductcaseBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPproductcase tBusiPproductcase = busiPproductCaseManager.addBusiPproductCase(bean);
        if(tBusiPproductcase.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPproductcase.getNavigationid1()+File.separator+tBusiPproductcase.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPproductcase.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPproductcase.getResult());
        result.put("failinfo", tBusiPproductcase.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PproductcaseBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPproductcase tBusiPproductcase = busiPproductCaseManager.updateBusiPproductCase(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPproductcase.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiPproductcase.getResult());
        result.put("failinfo", tBusiPproductcase.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PproductcaseBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiPproductcase tBusiPproductcase= busiPproductCaseManager.deleteBusiPproductCase(bean);
        result.put("code", tBusiPproductcase.getResult());
        result.put("failinfo", tBusiPproductcase.getFailinfo());
        return result;
    }


}
