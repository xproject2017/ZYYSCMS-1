package com.cms.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by whq on 2016/3/4.
 */
public class FileUpLoadDownLoadUtil {

    public static final String UPLOADDIR_STANDARD = System.getProperty("user.home") +"/standard/";

    public static final String UPLOADDIR_DEPT = System.getProperty("user.home") +"/dept/";

    public static final String UPLOADDIR_TEMP = System.getProperty("user.home") +"/TEMP/";

    public static final String UPLOADDIR_RES = System.getProperty("user.home") +"/RESULT/";

    public static final String UPLOADDIR_RISK = System.getProperty("user.home") +"/RISK/";

    public static final String UPLOADDIR_SCREEN_PIC = System.getProperty("user.home") +"/screen/pic/";

    public static final String UPLOADDIR_SCREEN_DATA = System.getProperty("user.home") +"/screen/data/";

    public static final String UPLOADDIR_SCREEN_STATIC = System.getProperty("user.home") +"/screen/static/";

    public static final String UPLOADDIR_SCREEN_COVER = System.getProperty("user.home") +"/screen/cover/";

    /**
     * 上传，并且进行文件校验，不通过直接返回false
     * @param request
     * @param uploadDir
     * @throws Exception
     */
    public static boolean uploadFile(HttpServletRequest request, String uploadDir, String[] suffix) throws Exception {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();

//        String uploadDir = request.getSession().getServletContext().getRealPath("/") + FileUpLoadDownLoadUtil.UPLOADDIR;

//        String uploadDir = null;
//
//        if (sid == null) {
//            uploadDir = FileUpLoadDownLoadUtil.UPLOADDIR_STANDARD + cid + "/temp/";
//        } else {
//            uploadDir = FileUpLoadDownLoadUtil.UPLOADDIR_STANDARD + cid + "/" + sid + "/";
//        }

        File file = new File(uploadDir);

        if (!file.exists()) {
            file.mkdirs();
        }

        FileOperateUtil.deleteDir(file);

        String fileName = null;
        int i = 0;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {

            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            fileName = mFile.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (suffix != null) {
                int suffx_i = 0;
                for (String s : suffix) {
                    if (s.trim().equalsIgnoreCase(fileSuffix)) {
                        break;
                    } else {
                        suffx_i++;
                    }
                }

                if (suffx_i == suffix.length) {
                    return false;
                }
            }

//            String storeName = rename(fileName);

            File storeFile = new File(uploadDir + fileName);

            if (!storeFile.exists()) {
                storeFile.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(storeFile);

            FileCopyUtils.copy(mFile.getInputStream(), fileOutputStream);


        }

        return true;

    }

    /**
     * 上传
     * @param request
     * @param uploadDir
     * @throws Exception
     */
    public static void uploadFile(HttpServletRequest request, String uploadDir) throws Exception {
        uploadFile(request, uploadDir, null);
    }

    /**
     * 上传单个文件
     * @param mFile
     * @param uploadDir
     * @throws Exception
     */
    public static void uploadFile(MultipartFile mFile, String uploadDir) throws Exception {
        uploadFile(mFile, uploadDir, null);
    }

    /**
     * 上传单个文件，并且进行文件校验，不通过直接返回false
     * @param mFile
     * @param uploadDir
     * @throws Exception
     */
    public static boolean uploadFile(MultipartFile mFile, String uploadDir, String[] suffix) throws Exception {

        File filePath = new File(uploadDir);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        FileOperateUtil.deleteDir(filePath);

        String fileName = mFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (suffix != null) {
            int i = 0;
            for (String s : suffix) {
                if (s.trim().equalsIgnoreCase(fileSuffix)) {
                    break;
                } else {
                    i++;
                }
            }

            if (i == suffix.length) {
                return false;
            }
        }

//            String storeName = rename(fileName);

        File storeFile = new File(uploadDir + fileName);

        if (!storeFile.exists()) {
            storeFile.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(storeFile);

        FileCopyUtils.copy(mFile.getInputStream(), fileOutputStream);

        return true;
    }


    /**
     * 下载
     * @param request
     * @param response
     * @param contentType
     * @throws Exception
     */
    public static HashMap<String, Object> downloadFile(HttpServletRequest request, HttpServletResponse response,
                                String contentType, String ctxPath) throws Exception {

        HashMap<String, Object> result = new HashMap<String, Object>();

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

//        String ctxPath = FileUpLoadDownLoadUtil.UPLOADDIR_STANDARD + cid + "/" + sid + "/";

        List<String> pathList = FileOperateUtil.getAllFilesInPath(ctxPath);

        if (pathList == null) {
            result.put("result", 1);
            result.put("failInfo", ResultInfo.ErrorNoDataFound);

            return result;
        }


        String downLoadPath = pathList.get(0);

        long fileLength = new File(downLoadPath).length();

        String realName = new File(downLoadPath).getName();

        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename="
                        + new String(realName.getBytes("gbk"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();

        result.put("result", 0);
        result.put("failInfo", null);

        return result;
    }
}
