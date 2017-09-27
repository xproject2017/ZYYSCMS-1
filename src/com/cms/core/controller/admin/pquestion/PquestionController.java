package com.cms.core.controller.admin.pquestion;

import com.cms.core.bean.cms.busi.PquestionBean;
import com.cms.core.bean.cms.core.TBusiPquestion;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPquestionManager;
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
 * 常见问题
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pquestion")
public class PquestionController {

    @Autowired
    private BusiPquestionManager busiPquestionManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getPquestionListPage")
    public ModelAndView getPquestionListPage(PquestionBean bean){
        ModelAndView view = new ModelAndView("/admin/pquestion/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addPquestionPage")
    public ModelAndView addPquestionPage(PquestionBean bean){
        ModelAndView view = new ModelAndView("/admin/pquestion/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updatePquestionPage")
    public ModelAndView updatePquestionPage(PquestionBean bean){
        ModelAndView view = new ModelAndView("/admin/pquestion/update");
        TBusiPquestion tBusiPquestion = busiPquestionManager.getBusiPquestion(bean);
        BeanUtils.copyProperties(tBusiPquestion,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPquestion.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getPquestionListData")
    @ResponseBody
    public Map<String,Object> getPquestionListData(PquestionBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPquestion>	list = busiPquestionManager.getBusiPquestionList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,PquestionBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPquestion tBusiPquestion = busiPquestionManager.addBusiPquestion(bean);
        if(tBusiPquestion.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPquestion.getNavigationid1()+File.separator+tBusiPquestion.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPquestion.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPquestion.getResult());
        result.put("failinfo", tBusiPquestion.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PquestionBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPquestion tBusiPquestion = busiPquestionManager.updateBusiPquestion(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPquestion.getResult() == 0 && null!=file  && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiPquestion.getResult());
        result.put("failinfo", tBusiPquestion.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PquestionBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPquestion tBusiPquestion = busiPquestionManager.deleteBusiPquestion(bean);
        result.put("code", tBusiPquestion.getResult());
        result.put("failinfo", tBusiPquestion.getFailinfo());
        return result;
    }



}
