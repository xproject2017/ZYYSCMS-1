package com.cms.core.controller.admin.recruit;

import com.cms.core.bean.cms.busi.RecruitBean;
import com.cms.core.bean.cms.core.TBusiRecruit;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiRecruitManager;
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
 * Created by zyy on 2016/3/21.
 * 招聘发布
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/recruit")
public class RecruitController {

    @Autowired
    private BusiRecruitManager busiRecruitManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getRecruitListPage")
    public ModelAndView getRecruitListPage(RecruitBean bean){
        ModelAndView view = new ModelAndView("/admin/recruit/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addRecruitPage")
    public ModelAndView addRecruitPage(RecruitBean bean){
        ModelAndView view = new ModelAndView("/admin/recruit/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateRecruitPage")
    public ModelAndView updateRecruitPage(RecruitBean bean){
        ModelAndView view = new ModelAndView("/admin/recruit/update");
        TBusiRecruit tBusiRecruit = busiRecruitManager.getBusiRecruit(bean);
        BeanUtils.copyProperties(tBusiRecruit,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiRecruit.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getRecruitListData")
    @ResponseBody
    public Map<String,Object> getRecruitListData(RecruitBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiRecruit>	list = busiRecruitManager.getBusiRecruitList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,RecruitBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiRecruit tBusiRecruit = busiRecruitManager.addBusiRecruit(bean);
        if(tBusiRecruit.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiRecruit.getNavigationid1()+File.separator+tBusiRecruit.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiRecruit.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiRecruit.getResult());
        result.put("failinfo", tBusiRecruit.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,RecruitBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiRecruit tBusiRecruit = busiRecruitManager.updateBusiRecruit(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiRecruit.getResult() == 0 && null!=file &&  null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiRecruit.getResult());
        result.put("failinfo", tBusiRecruit.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(RecruitBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TBusiRecruit tBusiRecruit = busiRecruitManager.deleteBusiRecruit(bean);
        result.put("code", tBusiRecruit.getResult());
        result.put("failinfo", tBusiRecruit.getFailinfo());
        return result;
    }
}
