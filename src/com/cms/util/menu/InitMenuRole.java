package com.cms.util.menu;

import com.cms.util.mysql.MySQLConnUtil;
import com.cms.util.PoiExcelUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.sql.*;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class InitMenuRole {

    public static void main(String[] args) throws SQLException {
        InitMenuRole initMenuRole=new InitMenuRole();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        conn = MySQLConnUtil.getConnection("checknetdb");
        //conn.setClientInfo("sql_mode","");
       // System.out.println(conn.getClientInfo());
        stmt = conn.createStatement();
        //stmt.executeUpdate("");

        String sql=null;
        int ucnt=0;


        //清空菜单，角色，角色菜单，账户 这4张表
        sql = "TRUNCATE TABLE T_SYS_MENU;";
        preStmt = conn.prepareStatement(sql);
        preStmt.executeUpdate();
        sql = "TRUNCATE TABLE T_SYS_ROLE;";
        preStmt = conn.prepareStatement(sql);
        preStmt.executeUpdate();
        sql = "TRUNCATE TABLE T_SYS_ROLEMENUS;";
        preStmt = conn.prepareStatement(sql);
        preStmt.executeUpdate();
        sql = "TRUNCATE TABLE T_SYS_STAFF;";
        preStmt = conn.prepareStatement(sql);
        preStmt.executeUpdate();

        //写入菜单
        sql = "TRUNCATE TABLE T_TMP_MENU;";
        preStmt = conn.prepareStatement(sql);
        preStmt.executeUpdate();
        sql="INSERT INTO T_TMP_MENU(NODEID,ORGID,NODELEVEL,NODECODE,NODETEXT,PATH,FNODEID,PLATFORMFLAG,`COMMENT`)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        String xlsfile = "E:\\project\\风控项目\\数据库\\应用软件菜单配置--20160330-impdb.xls";
        initMenuRole.menus2MySQL(xlsfile, sql, conn, stmt, preStmt, rs);
        sql="INSERT INTO T_SYS_MENU\n" +
                "SELECT NODEID+0,ORGID+0,NODELEVEL+0,NODECODE,NODETEXT,PATH,FNODEID+0,PLATFORMFLAG+0,`COMMENT`\n" +
                "FROM T_TMP_MENU\n" +
                "WHERE NODEID!='0.0' AND NODEID!='' AND NODEID!='0' AND NODEID!='NODEID'";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);


        //写入role   初始化admin角色和行政管理角色
        sql="INSERT INTO T_SYS_ROLE(ROLEID,ROLENAME)VALUES(1,'support'),(2,'admin'),(3,'行政管理')";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);

        //初始化角色菜单
        sql="INSERT INTO T_SYS_ROLEMENUS(ROLEID,NODEID)\n" +
                "SELECT 1,NODEID FROM T_SYS_MENU WHERE NODETEXT IN ('角色列表','账户列表','分中心账户')";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);
        sql="INSERT INTO T_SYS_ROLEMENUS(ROLEID,NODEID)\n" +
                "SELECT 2,NODEID FROM T_SYS_MENU";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);
        sql="INSERT INTO T_SYS_ROLEMENUS(ROLEID,NODEID)\n" +
                "SELECT 3,NODEID FROM T_SYS_MENU WHERE NODETEXT IN ('角色列表','账户列表','分中心账户')";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);

        //初始化admin账号
        sql="insert into `T_SYS_STAFF` (`USERID`, `ORGID`, `ROLEID`, `ROLEIDS`, `USTATUS`, `PERMISSIONFLAG`, `COMPANYID`, `FATHERID`, `USEREMAIL`, `USERMOBILEPHONE`, `USERNAME`, `USERPASSWORD`, `USERSTATUS`, `USERTELEPHONE`, `JOBNUMBER`, `DEPARTMENT`, `POST`, `UNAME`, `UFAX`, `CUSERID`, `CREATETIME`, `SAVETIME`) values('1','1','1','1','1','111','0','0','','13212341234','support','e10adc3949ba59abbe56e057f20f883e','2','54321123','111','0','超级系统管理员','管理员','2332112','0','2016-03-21 17:43:52','2016-03-24 14:39:59')";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);
        sql="insert into `T_SYS_STAFF` (`USERID`, `ORGID`, `ROLEID`, `ROLEIDS`, `USTATUS`, `PERMISSIONFLAG`, `COMPANYID`, `FATHERID`, `USEREMAIL`, `USERMOBILEPHONE`, `USERNAME`, `USERPASSWORD`, `USERSTATUS`, `USERTELEPHONE`, `JOBNUMBER`, `DEPARTMENT`, `POST`, `UNAME`, `UFAX`, `CUSERID`, `CREATETIME`, `SAVETIME`) values('2','1','2','2','1','111','0','0','','13212341234','admin','e10adc3949ba59abbe56e057f20f883e','2','54321123','111','0','系统管理员','管理员','2332112','0','2016-03-21 17:43:52','2016-03-24 14:39:59')";
        ucnt=stmt.executeUpdate(sql);
        System.out.println("ucnt=="+ucnt);

        conn.commit();
        MySQLConnUtil.close(rs, stmt, conn);
        MySQLConnUtil.close(rs, preStmt, conn);
    }
    public void menus2MySQL(String xlefile,String sql,Connection conn,Statement stmt,PreparedStatement preStmt,ResultSet rs){
        PoiExcelUtil eu = new PoiExcelUtil();
        List<Row> rowList;

        File file = new File(xlefile);
        if ((xlefile.endsWith(".xlsx") || xlefile.endsWith(".xls"))) {
            try {
                conn.setAutoCommit(false);
                preStmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

                rowList = eu.readExcel(file.getAbsolutePath());
                int i=0;
                String PRODUCTNAME=null;
                for(Row row :rowList){

                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        String value = eu.getCellValue(row.getCell(j));
                        //System.out.println(j+"=="+value);
                        preStmt.setString(j+1, value);

                    }
                    preStmt.addBatch();
                    i++;

                    if(i%500==0){
                        preStmt.executeBatch();
                        conn.commit();
                        conn.setAutoCommit(false);
                        preStmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    }
                }
                System.out.println(preStmt.toString());
                preStmt.executeBatch();
                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
