import com.cms.util.StringUtil;
import org.junit.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by clm on 2016/2/29.
 */
public class HSSFTestCase {

    @Test
    public void createTemplate(){
        try {
            File inputDir = new File("D:\\data\\imageupload\\0");
// 目标
            FileOutputStream fos = new FileOutputStream("D:\\CompressTest.zip");
// 过滤
            ZipOutputStream zos = new ZipOutputStream(fos);
// 压缩
            zip(inputDir.listFiles(), "", zos);
// 关闭
            zos.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void zip(File[] files, String baseFolder, ZipOutputStream zos)
            throws Exception {
// 输入
        FileInputStream fis = null;
// 条目
        ZipEntry entry = null;
// 数目
        int count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
// 递归
                zip(file.listFiles(), file.getName() + File.separator, zos);
                continue;
            }
            entry = new ZipEntry(baseFolder + file.getName());
// 加入
            zos.putNextEntry(entry);
            fis = new FileInputStream(file);
// 读取
            byte[] buffer = new byte[2048];
            while ((count = fis.read(buffer, 0, buffer.length)) != -1)
// 写入
                zos.write(buffer, 0, count);
        }
    }

    @Test
    public void testString(){
        String finalStr = StringUtil.generateCompNo(1,1);
        System.out.println("fin----"+finalStr);
    }
}
