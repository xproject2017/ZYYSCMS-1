package com.cms.core.controller.admin;

import com.cms.util.ResourceUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
/**
 * Created by zyy on 2016/3/21.
 * 获取图片
 */
@Controller
@Scope("prototype")
@RequestMapping("/view")
public class ImageController {

    @RequestMapping(value = "/getImage")
    public void getImage(String navigationid1,String navigationid2,String mesgid,HttpServletResponse response) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + navigationid1 +File.separator+ navigationid2 + File.separator + mesgid + ".jpg";
            File file = new File(imageuploadpath);
            if(!file.exists()){
                file = new File(getClass().getResource("/").toString().replace("WEB-INF/classes/","image").replace("file:/","")+File.separator+"no_image.gif");
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/downloadFile")
    public ModelAndView downloadOneFile(HttpServletResponse response,int id) throws IOException{
        String path =  ResourceUtil.getSystem("image.upload.path") + "apply" + File.separator + id;
        File prodFiles = new File(path);
        if (prodFiles.exists()&&prodFiles.isDirectory()){
            File[] allFiles = prodFiles.listFiles();
            prodFiles = allFiles[0];
        }else {
            ModelAndView mv = new ModelAndView("/downtips");
            mv.addObject("failinfo", "没有可下载的文件");
            return mv;
        }
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(prodFiles.getName().getBytes("gbk"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(prodFiles.length()));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(prodFiles));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();

        }catch (Exception e){
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(e.getMessage().getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            ModelAndView mv = new ModelAndView("/downtips");
            mv.addObject("failinfo", "没有可下载的文件");
            return mv;
        }
        return null;
    }
}
