package com.cms.core.controller.admin.pmultiplemsg;

import com.cms.core.bean.cms.busi.PmultiplemsgBean;
import com.cms.core.bean.cms.core.TBusiPmultiplemsg;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPmultiplemsgManager;
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
 * 公司综合信息
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pmultiplemsg")
public class PmultiplemsgController {

    @Autowired
    private BusiPmultiplemsgManager busiPmultiplemsgManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getPmultiplemsgListPage")
    public ModelAndView getPmultiplemsgListPage(PmultiplemsgBean bean){
        ModelAndView view = new ModelAndView("/admin/pmultiplemsg/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addPmultiplemsgPage")
    public ModelAndView addPmultiplemsgPage(PmultiplemsgBean bean){
        ModelAndView view = new ModelAndView("/admin/pmultiplemsg/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updatePmultiplemsgPage")
    public ModelAndView updatePmultiplemsgPage(PmultiplemsgBean bean){
        ModelAndView view = new ModelAndView("/admin/pmultiplemsg/update");
        String nodetext = bean.getNodetext();
        TBusiPmultiplemsg tBusiPmultiplemsg = busiPmultiplemsgManager.getBusiPmultiplemsg(bean);
        BeanUtils.copyProperties(tBusiPmultiplemsg,bean);
        bean.setNodetext(nodetext);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPmultiplemsg.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getPmultiplemsgListData")
    @ResponseBody
    public Map<String,Object> getPmultiplemsgListData(PmultiplemsgBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPmultiplemsg>	list = busiPmultiplemsgManager.getBusiPmultiplemsgList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request,PmultiplemsgBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPmultiplemsg tBusiPmultiplemsg = busiPmultiplemsgManager.addBusiPmultiplemsg(bean);
        if(tBusiPmultiplemsg.getResult() == 0 && null!=bean.getFileimage()){
            try {
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + tBusiPmultiplemsg.getNavigationid1()+File.separator+tBusiPmultiplemsg.getNavigationid2();
                File dir = new File(imageuploadpath);
                if (!dir.exists())
                    dir.mkdirs();
                File fos = new File(imageuploadpath + File.separator + tBusiPmultiplemsg.getMesgid() + ".jpg");
                BufferedImage bIMG = ImageIO.read(bean.getFileimage().getInputStream());
                Thumbnails.of(bean.getFileimage().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PmultiplemsgBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiPmultiplemsg tBusiPmultiplemsg = busiPmultiplemsgManager.updateBusiPmultiplemsg(bean);
        MultipartFile file = bean.getFileimage();
        if(tBusiPmultiplemsg.getResult() == 0  && null!=file && null != file.getOriginalFilename() && (!"".equals(file.getOriginalFilename()))){
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
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(PmultiplemsgBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPmultiplemsg tBusiPmultiplemsg = busiPmultiplemsgManager.deleteBusiPmultiplemsg(bean);
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }



}
