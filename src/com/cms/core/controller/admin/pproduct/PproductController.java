package com.cms.core.controller.admin.pproduct;

import com.cms.core.bean.cms.busi.PproductBean;
import com.cms.core.bean.cms.core.TBusiPproduct;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPproductManager;
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
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * Created by zyy on 2016/3/21.
 * 产品净值
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pproduct")
public class PproductController {

    @Autowired
    private BusiPproductManager busiPproductManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getProductListPage")
    public ModelAndView getProductListPage(PproductBean bean){
        ModelAndView view = new ModelAndView("/admin/pproduct/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addProductPage")
    public ModelAndView addProductPage(PproductBean bean){
        ModelAndView view = new ModelAndView("/admin/pproduct/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateProductPage")
    public ModelAndView updateProductPage(PproductBean bean){
        ModelAndView view = new ModelAndView("/admin/pproduct/update");
        TBusiPproduct tBusiPproduct = busiPproductManager.getBusiPproduct(bean);
        BeanUtils.copyProperties(tBusiPproduct,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPproduct.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
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
    public  Map<String,Object>  addData(HttpServletRequest request,PproductBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPproduct tBusiPproduct = busiPproductManager.addBusiPproduct(bean);
        if(tBusiPproduct.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPproduct.getNavigationid1()+File.separator+tBusiPproduct.getNavigationid2();
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
    }



}
