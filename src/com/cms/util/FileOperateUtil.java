package com.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * Created by whq on 2016/3/10.
 */
public class FileOperateUtil {

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {

            if (dir.list() == null) {
                return true;
            }

            String[] children = dir.list();
            //递归删除目录中的子目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return  false;
                }
            }
        } else {
            return dir.delete();
        }

        // 目录此时为空，可以删除
        return true;
    }

    /**
     * 移动文件
     * @param oldFileDir
     * @param newPath
     * @return
     */
    public static boolean moveFile(String oldFileDir, String newPath) {
        // 文件原地址
        File oldFile = new File(oldFileDir);

        //文件新（目标）地址
        File fnewpath = new File(newPath);

        if (!fnewpath.exists()) {
            fnewpath.mkdirs();
        }

        deleteDir(fnewpath);

        File fnew = new File(newPath + oldFile.getName());
        return oldFile.renameTo(fnew);
    }
    /**
     * 移动文件
     * @param from 原始位置文件
     * @param to 目标位置文件
     * @Author chenlm
     * @return
     */
    public static void copyFile(String from, String to) throws IOException {
        File inFile = new File(from);
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(to);
        fis.getChannel().transferTo(0, fis.getChannel().size(), fos.getChannel());
        inFile.delete();
        fos.close();
        fis.close();
    }

    /**
     * 获取某个文件夹下面的所有文件，如果没有文件则返回null
     * @param path 以“/”结尾的路径
     * @return
     */
    public static List<String> getAllFilesInPath(String path) {

        List<String> fileList = new ArrayList<String>();

        File file = new File(path);

        if (file == null || file.listFiles() == null) {
            return null;
        }

        File[] array = file.listFiles();

        for (File f : array) {
            if (f.isFile()) {
                fileList.add(f.getAbsolutePath());
            }
        }

        return  fileList;
    }
}
