package com.cms.util;

import com.cms.core.bean.cms.core.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/24.
 */
public class Bean2MySQL4Mybatis {

   /*
   <typeAlias type="com.casystem.common.mode.tag.TagBeanWly" alias="TagBeanWly"/>


    import com.casystem.common.mode.appserver.base.TSysStaff;

    import java.util.HashMap;
    import java.util.List;

    /*
     * Created by zhouxx on 2015-3-4.
     */
  /*  public interface UserDao {
        /*权限--系统用户*/
 /*       public TSysStaff getUserInfo(TSysStaff usersBean);
        public List<TSysStaff> getUserList(TSysStaff usersBean);
        public HashMap getUserListGroups(TSysStaff usersBean);  //返回count,max,min
        public int addUsers(TSysStaff usersBean);
        public int updateUsers(TSysStaff usersBean);
        public int deleteUsers(TSysStaff usersBean);

    }

    MYSQL> SHOW TABLES;
+----------------------+
| TABLES_IN_CMSDB      |
+----------------------+
| T_BUSI_APPLY         |
| T_BUSI_NAVIGATIONID1 |
| T_BUSI_NAVIGATIONID2 |
| T_BUSI_PBESPOKE      |
| T_BUSI_PCONSULTATION |
| T_BUSI_PMULTIPLEMSG  |
| T_BUSI_PNES          |
| T_BUSI_PNOTICE       |
| T_BUSI_PPRODUCT      |
| T_BUSI_PQUESTION     |
| T_BUSI_RECRUIT       |
| T_BUSI_VIP           |

+----------------------+
17 ROWS IN SET (0.00 SEC)


    */
   static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String args[]) {
        try {
            Class c = TBusiTestpaper.class;
            String TableName="T_BUSI_TESTPAPER";
            String mybatis_typeAlias="BusiTestpaper";
            String packagename=c.getName();
            String endwithfunname=packagename.substring(packagename.lastIndexOf(".") + 2);
            mybatis_typeAlias=endwithfunname;
            System.out.println(packagename+" , "+endwithfunname);
            String comments="标准DAO";

            daoInterface(c,comments,endwithfunname);
            mybatisAlias(c,mybatis_typeAlias);
            selectSql(c, TableName, endwithfunname, mybatis_typeAlias);
            selectSql_MySQL_pageGroups(c, TableName, endwithfunname, mybatis_typeAlias);
            selectSql_MySQL_page(c, TableName, endwithfunname, mybatis_typeAlias);
            addSql(c, TableName, endwithfunname, mybatis_typeAlias);
            updateSql(c, TableName, endwithfunname, mybatis_typeAlias);
            deleteSql(c, TableName, endwithfunname, mybatis_typeAlias);

//            selectSql(c,TableName);
//            selectSql_MySQL_page(c,TableName);
//            selectSql_MySQL_pageGroups(c,TableName);//返回分页查询总记录数，用于前台计算页数
//            addSql(c,TableName); //修改savetime和createtime的默认值now() ，返回主键自增值
//            updateSql(c,TableName);//修改savetime的默认值now()  ,createtime不允许修改  ;并发控制，增加savetime条件
//            deleteSql(c,TableName); //并发控制，增加savetime条件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void daoInterface(Class c,String comments,String endwithfunname) {
        System.out.println("------------新增" + c.getName() + "对象Dao接口--------------");
        String packagename=c.getName();
        String classname=c.getSimpleName().trim();
        String daoname=endwithfunname.substring(0,1).toUpperCase()+endwithfunname.substring(1)+"Dao";
       // System.out.println(packagename+" , "+endwithfunname);

        StringBuilder classInfo=new StringBuilder();

        classInfo.append("import\t"+packagename+";\r\n");
        classInfo.append("import java.util.HashMap;\r\n");
        classInfo.append("import java.util.List;\r\n");

        classInfo.append("\t/**\r\n");
        classInfo.append("\t****" + comments + "\r\n");
        classInfo.append("\t*此Dao接口由" + classname + "类自动生成\r\n");
        classInfo.append("\t*@author zhouxx\r\n");
        classInfo.append("\t*@since ");
        classInfo.append(sdf.format(new Date()));
        classInfo.append("\r\n\t*/\r\n\r\n");
        classInfo.append("\tpublic interface ").append(daoname).append("{\r\n");

        classInfo.append("\t\tpublic "+classname+"\t"+"get"+endwithfunname+"("+classname+"\tbean);\r\n");
        classInfo.append("\t\tpublic List<"+classname+">\t"+"get"+endwithfunname+"List("+classname+"\tbean);\n");
        classInfo.append("\t\tpublic HashMap\t"+"get"+endwithfunname+"Group("+classname+"\tbean);\n");
        classInfo.append("\t\tpublic int\t"+"add"+endwithfunname+"("+classname+"\tbean);\n");
        classInfo.append("\t\tpublic int\t"+"update"+endwithfunname+"("+classname+"\tbean);\n");
        classInfo.append("\t\tpublic int\t"+"delete"+endwithfunname+"("+classname+"\tbean);\n");

        classInfo.append("\r\n");
        classInfo.append("}");

        System.out.println(classInfo.toString());
        System.out.println("------------新增" + c.getName() + "对象Dao接口结束--------------\n");
    }
    public static void    mybatisAlias(Class c,String mybatis_typeAlias){
       // <typeAlias type="com.casystem.common.mode.tag.TagBeanWly" alias="TagBeanWly"/>
        System.out.println("------------新增" + c.getName() + "对象MyBatis的别名--------------");
        StringBuilder typeAlias=new StringBuilder();
        typeAlias.append("<typeAlias \t");
        typeAlias.append("type=\""+c.getName()+"\" \t");
        typeAlias.append("alias=\""+mybatis_typeAlias+"\" \t");
        typeAlias.append("/>\r\n");
        System.out.println(typeAlias.toString());
        System.out.println("------------新增" + c.getName() + "对象MyBatis的别名结束--------------");
    }
    public static void addSql(Class c,String TableName,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------新增" + c.getName() + "对象sql语句------(单条insert)--------");
        Field[] fields = c.getDeclaredFields();
        String addSql = "<insert id=\"add"+endwithfunname+"\" useGeneratedKeys=\"true\" keyProperty=\"primaryKey\" parameterType=\""+mybatis_typeAlias+"\">\r\nINSERT INTO " + TableName + " (";
        for (Field f : fields) {
            addSql += f.getName().toUpperCase() + ",";
        }
        addSql = addSql.substring(0, addSql.length() - 1) + ")\r\nvalues(";
        for (Field f : fields) {
            if ("CREATETIME".equals(f.getName().toUpperCase())||"SAVETIME".equals(f.getName().toUpperCase())) {
                addSql += ("NOW(),");
            } else {
                addSql += ("#{" + f.getName() + "},");
            }
        }
        addSql = addSql.substring(0, addSql.length() - 1) + ")\r\n</insert>";
        System.out.println(addSql);
        System.out.println("------------新增" + c.getName() + "对象sql语句结束-----------");
    }

    public static void addSql(Class c,String TableName) {
        System.out.println("------------新增" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String addSql = "<insert id=\"MedhodName\" useGeneratedKeys=\"true\" keyProperty=\"primaryKey\" parameterType=\"BeanName\">\r\ninsert into " + TableName + " (";
        for (Field f : fields) {
            addSql += f.getName().toUpperCase() + ",";
        }
        addSql = addSql.substring(0, addSql.length() - 1) + ") \r\nvalues(";
        for (Field f : fields) {
            if ("CREATETIME".equals(f.getName().toUpperCase())||"SAVETIME".equals(f.getName().toUpperCase())) {
                addSql += ("NOW(),");
            } else {
                addSql += ("#{" + f.getName() + "},");
            }
        }
        addSql = addSql.substring(0, addSql.length() - 1) + ")</insert>";
        System.out.println(addSql);
        System.out.println("------------新增" + c.getName() + "对象sql语句结束-----------");
    }

    public static void updateSql(Class c,String TableName,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------更新" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String updateSql = "<update id=\"update"+endwithfunname+"\" parameterType=\""+mybatis_typeAlias+"\">\r\n" +
                "update "+ TableName+"\n<set>\n";
        String variableName ="";
        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                updateSql += ("<if test=\"" + 1 + "==1\">" + "" + "SAVETIME=NOW(),</if>\r\n");
            } else {
                if (!"CREATETIME".equals(f.getName().toUpperCase())) {
                    variableName = f.getName();
                    updateSql += ("<if test=\"" + variableName + "!=null\">" + variableName.toUpperCase() + "=#{" + variableName + "},</if>\r\n");
                }

            }
        }

        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                variableName = f.getName();
            }
        }
        updateSql += "</set>\n where primarykey = #{primarykey}\n" +
                " AND SAVETIME=#{" + variableName + "} \r\n</update>";
        System.out.println(updateSql);
        System.out.println("------------更新" + c.getName() + "对象sql语句结束----------\r\n");
    }

    public static void updateSql(Class c,String TableName) {
        System.out.println("------------更新" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String updateSql = "<update id=\"MethodName\" parameterType=\"BeanName\">\r\n" +
                "update "+ TableName+"\n<set>\n";
        String variableName ="";
        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                updateSql += ("<if test=\"" + 1 + "==1\">" + "" + "SAVETIME=NOW(),</if>\r\n");
            } else {
                if (!"CREATETIME".equals(f.getName().toUpperCase())) {
                    variableName = f.getName();
                    updateSql += ("<if test=\"" + variableName + "!=null\">" + variableName.toUpperCase() + "=#{" + variableName + "},</if>\r\n");
                }

            }
        }

        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                variableName = f.getName();
            }
        }
        updateSql += "</set>\n where primarykey = #{primarykey}\n" +
                " AND SAVETIME=#{" + variableName + "} \r\n</update>";
        System.out.println(updateSql);
        System.out.println("------------更新" + c.getName() + "对象sql语句结束----------\n");
    }
    public static void deleteSql(Class c,String TableName,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------删除" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String variableName ="";
        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                variableName = f.getName();
            }
        }
        String updateSql = "<delete id=\"delete"+endwithfunname+"\" parameterType=\""+mybatis_typeAlias+"\">\r\n" +
                "delete from "+ TableName+"\n where primarykey = #{primarykey} AND SAVETIME=#{" + variableName + "}" +
                "\r\n</delete>";
        System.out.println(updateSql);
        System.out.println("------------删除" + c.getName() + "对象sql语句结束----------\n");
    }

    public static void deleteSql(Class c,String TableName) {
        System.out.println("------------删除" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String variableName ="";
        for (Field f : fields) {
            if ("SAVETIME".equals(f.getName().toUpperCase())) {
                variableName = f.getName();
            }
        }
        String updateSql = "<delete id=\"MethodName\" parameterType=\"BeanName\">\r\n" +
                "delete from "+ TableName+"\n where primarykey = #{primarykey} AND SAVETIME=#{" + variableName + "}   \n" +
                "</delete>";
        System.out.println(updateSql);
        System.out.println("------------删除" + c.getName() + "对象sql语句结束----------\n");
    }
    public static void selectSql(Class c,String TableName ,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------查询" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"get"+endwithfunname+"\" parameterType=\""+mybatis_typeAlias+"\" resultType=\""+mybatis_typeAlias+"\">\r\nselect * from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "\n</where>\r\n</select>";
        System.out.println(selectSql);
        System.out.println("------------查询" + c.getName() + "对象sql语句结束----------\n");
    }

    public static void selectSql(Class c,String TableName ) {
        System.out.println("------------查询" + c.getName() + "对象sql语句--------------");
        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"MethodName\" parameterType=\"BeanName\" resultType=\"BeanName\">\r\nselect * from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "\n</where>\r\n</select>";
        System.out.println(selectSql);
        System.out.println("------------查询" + c.getName() + "对象sql语句结束----------\n");
    }

    public static void selectSql_MySQL_page(Class c,String TableName,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------分页查询" + c.getName() + "对象sql语句--------------");
        System.out.println("------------分页查询sql语句：入库时间CREATETIME字段上要有索引，且分页的where条件中一定要带有CREATETIME条件--------------");
        System.out.println("------------分页查询sql语句：最后一次更新时间SAVETIME字段用于查询结果的排序--------------");

        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"get"+endwithfunname+"List\" parameterType=\""+mybatis_typeAlias+"\" resultType=\""+mybatis_typeAlias+"\">\r\nselect * from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "</where> ORDER BY CREATETIME DESC \r\n " +
                "<if test=\"currentPage!=null and pageSize!=null\">\n" +
                "            <![CDATA[ LIMIT #{currentSize}, #{pageSize} ]]>\n" +
                "        </if></select>";
        System.out.println(selectSql);
        System.out.println("------------分页查询" + c.getName() + "对象sql语句结束----------\n");
    }
    public static void selectSql_MySQL_page(Class c,String TableName) {
        System.out.println("------------分页查询" + c.getName() + "对象sql语句--------------");
        System.out.println("------------分页查询sql语句：入库时间CREATETIME字段上要有索引，且分页的where条件中一定要带有CREATETIME条件--------------");
        System.out.println("------------分页查询sql语句：最后一次更新时间SAVETIME字段用于查询结果的排序--------------");

        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"MethodName\" parameterType=\"BeanName\" resultType=\"BeanName\">\r\nselect * from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "</where> ORDER BY SAVETIME DESC \r\n " +
                "<if test=\"currentPage!=null and pageSize!=null\">\n" +
                "            <![CDATA[ LIMIT #{currentSize}, #{pageSize} ]]>\n" +
                "        </if></select>";
        System.out.println(selectSql);
        System.out.println("------------分页查询" + c.getName() + "对象sql语句结束----------");
    }
    public static void selectSql_MySQL_pageGroups(Class c,String TableName,String endwithfunname,String mybatis_typeAlias) {
        System.out.println("------------分页查询" + c.getName() + "对象sql语句--------------");
        System.out.println("------------分页查询sql语句：入库时间CREATETIME字段上要有索引，且分页的where条件中一定要带有CREATETIME条件--------------");
        System.out.println("------------分页查询sql语句：最后一次更新时间SAVETIME字段用于查询结果的排序--------------");

        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"get"+endwithfunname+"Group\" parameterType=\""+mybatis_typeAlias+"\" resultType=\"hashMap\">\r\nselect IFNULL(COUNT(ID),0) CNT,IFNULL(MAX(ID),0) MAXID,IFNULL(MIN(ID),0) MINID from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "\r\n</where>\r\n</select>";
        System.out.println(selectSql);
        System.out.println("------------分页查询" + c.getName() + "对象sql语句结束----------\n");
    }
    public static void selectSql_MySQL_pageGroups(Class c,String TableName) {
        System.out.println("------------分页查询" + c.getName() + "对象sql语句--------------");
        System.out.println("------------分页查询sql语句：入库时间CREATETIME字段上要有索引，且分页的where条件中一定要带有CREATETIME条件--------------");
        System.out.println("------------分页查询sql语句：最后一次更新时间SAVETIME字段用于查询结果的排序--------------");

        Field[] fields = c.getDeclaredFields();
        String selectSql = "<select id=\"MethodNameGroups\" parameterType=\"BeanName\" resultType=\"hashMap\">\r\nselect IFNULL(COUNT(ID),0) CNT,IFNULL(MAX(ID),0) MAXID,IFNULL(MIN(ID),0) MINID from "+ TableName+"\r\n<where>\r\n";
        String variableName = fields[0].getName();
        selectSql += ("<if test=\"" + variableName + "!=null\"> \r\n" + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
        for (int i = 1; i < fields.length; i++) {
            if(!"SAVETIME".equals(fields[i].getName().toUpperCase())){
                if("CREATETIME".equals(fields[i].getName().toUpperCase())){
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + ">=#{" + variableName + "}</if>\r\n");
                }else{
                    variableName = fields[i].getName();
                    selectSql += ("<if test=\"" + variableName + "!=null\"> \r\nAND " + variableName.toUpperCase() + "=#{" + variableName + "}</if>\r\n");
                }
            }
        }
        selectSql += "</where> </select>";
        System.out.println(selectSql);
        System.out.println("------------分页查询" + c.getName() + "对象sql语句结束----------\n");
    }

//    public static void roll(Class c) {
//        try {
//
//            Class c = null;
//            c = Role.class;
//
//            int batch_insert_cnt = 20000;
//            int cnt = 51000;
//            int p = 0;
//            if (cnt % batch_insert_cnt == 0) {
//                p = cnt / batch_insert_cnt;
//            } else {
//                p = Math.round(cnt / batch_insert_cnt) + 1;      //10w条记录分批次insert，每次最多2w.
//            }
//            Long cnt_p = 0l;
//            System.out.println("p=" + p);
//            for (int i = 1; i <= p; i++) {
//                if (i == p) {
//                    cnt_p = (long) (cnt - (p - 1) * batch_insert_cnt);
//                    System.out.println("i=" + i + ",cnt_p=" + cnt_p);
//                } else {
//                    cnt_p = (long) batch_insert_cnt;
//                    System.out.println("i=" + i + ",cnt_p=" + (cnt_p));
//                }
//            }
//
////            addSql(c);
////            updateSql(c);
////            selectSql(c);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
