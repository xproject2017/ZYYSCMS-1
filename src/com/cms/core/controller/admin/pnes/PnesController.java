package com.cms.core.controller.admin.pnes;

import com.cms.core.bean.cms.busi.PnesBean;
import com.cms.core.bean.cms.core.TBusiPnes;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPnesManager;
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
 * 公司动态
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pnes")
public class PnesController {

    @Autowired
    private BusiPnesManager busiPnesManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getPnesListPage")
    public ModelAndView getPnesListPage(PnesBean bean){
        ModelAndView view = new ModelAndView("/admin/pnes/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addPnesPage")
    public ModelAndView addPnesPage(PnesBean bean){
        ModelAndView view = new ModelAndView("/admin/pnes/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updatePnesPage")
    public ModelAndView updatePnesPage(PnesBean bean){
        ModelAndView view = new ModelAndView("/admin/pnes/update");
        TBusiPnes tBusiPnes = busiPnesManager.getBusiPnes(bean);
        BeanUtils.copyProperties(tBusiPnes,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPnes.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getPnesListData")
    @ResponseBody
    public Map<String,Object> getPnesListData(PnesBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPnes>	list = busiPnesManager.getBusiPnesList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,PnesBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPnes tBusiPnes = busiPnesManager.addBusiPnes(bean);
        if(tBusiPnes.getResult() == 0  && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPnes.getNavigationid1()+File.separator+tBusiPnes.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPnes.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPnes.getResult());
        result.put("failinfo", tBusiPnes.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PnesBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPnes tBusiPnes = busiPnesManager.updateBusiPnes(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPnes.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiPnes.getResult());
        result.put("failinfo", tBusiPnes.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PnesBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPnes tBusiPnes = busiPnesManager.deleteBusiPnes(bean);
        result.put("code", tBusiPnes.getResult());
        result.put("failinfo", tBusiPnes.getFailinfo());
        return result;
    }



}
