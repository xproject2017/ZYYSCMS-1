package com.cms.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * @author 周祥兴
 * @date 2016年03月03日
 */
public class StringUtil {

    /*-----------------------------------
  笨方法：String s = "你要去除的字符串";
          1.去除空格：s = s.replace('\\s','');
          2.去除回车：s = s.replace('\n','');
  这样也可以把空格和回车去掉，其他也可以照这样做。
  注：\n 回车(\u000a)
  \t 水平制表符(\u0009)
  \s 空格(\u0008)
  \r 换行(\u000d)
     * @author 周祥兴
     * @return String
     * @date 2016年03月03日
  */
    public static String replaceBlank(String str) {
        String dest = null;
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 功能：对String字段进行处理，如果是“”或者“ ”全部都转成null
     * 用于在传对象参数到MyBatis中的空值处理（空串和空格）
     *
     * @param from 源对象
     * @param dest 目标对象
     * @return void
     * @author 周祥兴
     * @date 2016年03月03日
     */
    public static void trimObjectStringField(Object from, Object dest) throws Exception {
        // 取得拷贝源对象的Class对象
        Class<?> fromClass = from.getClass();
        // 取得拷贝源对象的属性列表
        Field[] fromFields = fromClass.getDeclaredFields();
        // 取得拷贝目标对象的Class对象
        Class<?> destClass = from.getClass();
        Field destField = null;
        for (Field fromField : fromFields) {
            // 取得拷贝源对象的属性名字
            String name = fromField.getName();
            // 取得拷贝目标对象的相同名称的属性
            destField = destClass.getDeclaredField(name);
            // 设置属性的可访问性
            fromField.setAccessible(true);
            destField.setAccessible(true);


            //System.out.println(name + "," + fromField.toString() + "," + fromField.getType().getSimpleName());
            if (fromField.get(from) != null) {
                // System.out.println("value===" + fromField.get(from).toString());


                if ("String".toUpperCase().equals(fromField.getType().getSimpleName().toUpperCase())) {
                    // 将拷贝源对象的属性的值赋给拷贝目标对象相应的属性
                    if (fromField.get(from) != null) {
                        if (!"".equals(replaceBlank(fromField.get(from).toString().trim()))) {
                            destField.set(dest, fromField.get(from));
                        }
                    }

                } else {
                    // 将拷贝源对象的属性的值赋给拷贝目标对象相应的属性
                    destField.set(dest, fromField.get(from));
                }
            }
        }
    }

    /**
     * 根据字段名称赋值
     *
     * @param c         对象
     * @param fieldName 属性名称
     * @param valutype  属性值类型
     * @param value     值  Parameter
     * @return
     * @author 周祥兴
     */
    public static void setClassValue(Object c, String fieldName, int valutype, Parameter value) {
        if (c == null || fieldName == null) {
            return;
        }
        if ("".equals(fieldName.trim())) {
            return;
        }
        try {
            Class beanClass = c.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                if (ms[i].getName().startsWith("get") || ms[i].getName().startsWith("set")) {
                    //System.out.println(ms[i].getName());
                }
                if (ms[i].getName().toLowerCase().endsWith(fieldName) && ms[i].getName().startsWith("set")) {
                    System.out.println(ms[i].getName());
                    if (valutype == Parameter.IntegerType) {
                        Method m = c.getClass().getMethod(ms[i].getName(), Integer.class);
                        m.invoke(c, value.getIntegerValue());
                    }
                    if (valutype == Parameter.StringType) {
                        Method m = c.getClass().getMethod(ms[i].getName(), String.class);
                        m.invoke(c, value.getStringValue());
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给对象赋初始值
     * 数值型：0
     * 字符型：“”
     * 布尔型：false
     * 日期：当前时间
     *
     * @param c 对象
     *          1.获取实体的所有字段,遍历
     *          2.获取字段类型
     *          3.调用字段的get方法,判断字段值是否为空
     *          4.如果字段值为空,调用字段的set方法,为字段赋值
     * @return
     * @author 周祥兴
     */
    public static void setClassDefaultValue(Object c) {
        if (c == null) {
            return;
        }
        Field[] field = c.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                System.out.println(name + ",");
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String getname = "get" + name;
                String setname = "set" + name;
                String type = field[j].getGenericType().toString(); // 获取属性的类型
                System.out.println(name + "," + type);
                System.out.println(setname + "," + getname);

                //String
                if (type.endsWith("java.lang.String")) {
                    Method m = c.getClass().getMethod(getname);
                    String value = (String) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, String.class);
                        m.invoke(c, "");
                    }
                }
                //Integer
                if (type.endsWith("java.lang.Integer")) {
                    Method m = c.getClass().getMethod(getname);
                    Integer value = (Integer) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, Integer.class);
                        m.invoke(c, 0);
                    }
                }
                //Double
                if (type.endsWith("java.lang.Double")) {
                    Method m = c.getClass().getMethod(getname);
                    Double value = (Double) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, Integer.class);
                        m.invoke(c, 0.0);
                    }
                }
                //Long
                if (type.endsWith("java.lang.Long")) {
                    Method m = c.getClass().getMethod(getname);
                    Long value = (Long) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, Integer.class);
                        m.invoke(c, 0l);
                    }
                }
                //Boolean
                if (type.endsWith("java.lang.Boolean")) {
                    Method m = c.getClass().getMethod(getname);
                    Boolean value = (Boolean) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, Boolean.class);
                        m.invoke(c, false);
                    }
                }
                //Date
                if (type.endsWith("java.util.Date")) {
                    Method m = c.getClass().getMethod(getname);
                    Date value = (Date) m.invoke(c); // 调用getter方法获取属性值
                    if (value == null) {
                        m = c.getClass().getMethod(setname, Date.class);
                        m.invoke(c, new Date());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将对象中的String字段对应的空值“”和空格“ ”转成null
     *
     * @param c 对象
     * @return
     * @author 周祥兴
     */
    public static void setClassStringFiledNull(Object c) {
        if (c == null) {
            return;
        }
        Field[] field = c.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                System.out.println(name + ",");
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String getname = "get" + name;
                String setname = "set" + name;
                String type = field[j].getGenericType().toString(); // 获取属性的类型
                System.out.println(name + "," + type);
                System.out.println(setname + "," + getname);

                //String
                if (type.endsWith("java.lang.String")) {
                    Method m = c.getClass().getMethod(getname);
                    String value = (String) m.invoke(c); // 调用getter方法获取属性值
                    if (value != null) {
                        value = StringUtil.replaceBlank(value);
                        if ("".equals(value)) {
                            m = c.getClass().getMethod(setname, String.class);
                            m.invoke(c, (Object) null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map<String, String[]> getAddstr(String addstr) {
        Map<String, String[]> cityStr = new HashMap<String, String[]>();
        try {
            String str = new String(addstr.getBytes("ISO-8859-1"), "utf-8");
            String st[] = str.split(",");
            cityStr.put("city", st);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cityStr;
    }

    //投诉举报中生成编号，网络投诉a+8位数字，电话投诉b+8位数字
    //type:1网络 2电话 例：电话投诉输入id为1，方法返回b00000001
    public static String generateCompNo(Integer id,Integer type) {
        if (id == null) return null;
        String sb = id.toString();
        int len = id.toString().length();
        if (len<8) {
            for (int i = 8 - len; i > 0; i--) {
                sb = "0" + sb;
            }
        }
        if (type == 1){
            sb = "a" + sb;
        }else {
            sb = "b" + sb;
        }

        return sb;
    }

    //展示中心根据id和来源自动生成编号
    public static String generateNo4Scree(Integer id,Integer source) {
        if (id == null) return null;
        String sb = id.toString();
        int len = id.toString().length();
        if (source !=6&&source!=5) {
            if (len < 8) {
                for (int i = 8 - len; i > 0; i--) {
                    sb = "0" + sb;
                }
            }
            sb = Constants.SOURCE_DATA_FORSCREEN_NUMBER[source] + sb;
        }else {
            if (len<2){
                for (int i = 2 - len; i > 0; i--) {
                    sb = "0" + sb;
                }
            }
        }

        return sb;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {                                    //转化为16进制字符串
            byte b1 = (byte) ((bytes[i] & 0xf0) >> 4);
            byte b2 = (byte) (bytes[i] & 0x0f);
            if (b1 < 10)
                result.append((char) ('0' + b1));
            else
                result.append((char) ('A' + (b1 - 10)));
            if (b2 < 10)
                result.append((char) ('0' + b2));
            else
                result.append((char) ('A' + (b2 - 10)));
        }
        return result.toString();
    }

    public static String generateSessionId(Long userId) {

        Random random = new SecureRandom();
        byte randomBytes[] = new byte[8];
        random.nextBytes(randomBytes);

        Date dateNow = new Date(System.currentTimeMillis());
        String token = byteToHexString(randomBytes) + Long.toHexString(userId | 0xF000000000000000L).substring(12, 16)
                + Long.toHexString(dateNow.getTime() | 0xF000000000000000L).substring(4, 16);
        return token.toUpperCase();
    }
}
