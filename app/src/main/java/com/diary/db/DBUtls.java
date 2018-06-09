package com.diary.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtls {
    private static String url;
    private static String user;
    private static String password;

    //加载配置文件，提取连接数据库的初始化数据
    static {
        //InputStream is = DBUtls.class.getClassLoader().getResourceAsStream("jdbcInfo.properties");
        //Properties props = new Properties();
        try {
            //  props.load(is);
            String driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://sh-cdb-1fia2epv.sql.tencentcdb.com:63097/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
            user = "root";
            password = "ZY20120527";
            //	System.out.println(url);

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection() {

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //用完连接后则释放连接
    public static void CloseResource(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }

}
