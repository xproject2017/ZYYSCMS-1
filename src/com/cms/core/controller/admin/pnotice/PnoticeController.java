package com.cms.core.controller.admin.pnotice;

import com.cms.core.bean.cms.busi.PnoticeBean;
import com.cms.core.bean.cms.core.TBusiPnotice;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPnoticeManager;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyy on 2016/3/21.
 * 公告信息
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pnotice")
public class PnoticeController {

    @Autowired
    private BusiPnoticeManager busiPnoticeManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getNoticeListPage")
    public ModelAndView getNoticeListPage(PnoticeBean bean){
        ModelAndView view = new ModelAndView("/admin/pnotice/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addNoticePage")
    public ModelAndView addNoticePage(PnoticeBean bean){
        ModelAndView view = new ModelAndView("/admin/pnotice/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateNoticePage")
    public ModelAndView updateNoticePage(PnoticeBean bean){
        ModelAndView view = new ModelAndView("/admin/pnotice/update");
        TBusiPnotice tBusiPnotice = busiPnoticeManager.getBusiPnotice(bean);
        BeanUtils.copyProperties(tBusiPnotice,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPnotice.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getNoticeListData")
    @ResponseBody
    public Map<String,Object> getNoticeListData(PnoticeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPnotice>	list = busiPnoticeManager.getBusiPnoticeList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,PnoticeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPnotice tBusiPnotice = busiPnoticeManager.addBusiPnotice(bean);
        if(tBusiPnotice.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPnotice.getNavigationid1()+File.separator+tBusiPnotice.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPnotice.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPnotice.getResult());
        result.put("failinfo", tBusiPnotice.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PnoticeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPnotice tBusiPnotice = busiPnoticeManager.updateBusiPnotice(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPnotice.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiPnotice.getResult());
        result.put("failinfo", tBusiPnotice.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PnoticeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPnotice tBusiPnotice = busiPnoticeManager.deleteBusiPnotice(bean);
        result.put("code", tBusiPnotice.getResult());
        result.put("failinfo", tBusiPnotice.getFailinfo());
        return result;
    }
}
