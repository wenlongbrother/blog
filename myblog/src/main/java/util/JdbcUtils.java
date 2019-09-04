package util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Jdbc工具类
 * @author 蒋文龙
 */
public class JdbcUtils {
    static DataSource dataSource = null ;
    static {
        try {
            dataSource = BasicDataSourceFactory.createDataSource(getProperties());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到DataSource连接资源
     * @return dataSource
     */
    public static DataSource getDataSource() {
        return dataSource ;
    }

    /**
     * 得到Connection连接
     * @return Connection
     * @throws SQLException
     */
    public  static Connection getConnection() throws SQLException {

        return dataSource.getConnection();

    }
    /**
     * 得到配置文件
     * @return properties
     */
    private static Properties getProperties() {
        Properties properties = null ;
        try {
            properties = new Properties() ;
//			InputStream is = new FileInputStream("E:\\project\\code\\fruit\\fruit\\src\\main\\java\\com\\qinfenfeng\\util\\dbcpconfig.properties");
//            使用类加载器，去读取资源文件。
            InputStream is =JdbcUtils.class.getClassLoader().getResourceAsStream("util/jdbc.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void release(Connection conn , Statement st , ResultSet rs){
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }
    public static void release(Connection conn , Statement st){
        closeSt(st);
        closeConn(conn);
    }


    private static void closeRs(ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            rs = null;
        }
    }

    private static void closeSt(Statement st){
        try {
            if(st != null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            st = null;
        }
    }

    private static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn = null;
        }
    }
}
