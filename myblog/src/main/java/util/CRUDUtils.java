package util;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 蒋文龙的工具类
 */
public class CRUDUtils {
    /**
     * 通用的【增删改】方法
     * @param sql 要执行的sql语句
     * @param args sql语句带的参数
     * @return 返回执行成功的行数
     */
    public static int update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        int len = 0;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ParameterMetaData metaData = ps.getParameterMetaData();
            int count = metaData.getParameterCount();
            // 取到sql语句的参数
            for (int i = 0; i < count; i++) {
                ps.setObject(i +1, args[i]);
//                System.out.println(args[i]);
            }
            len = ps.executeUpdate();
            System.out.println("【CommonCRUDUtil:update】成功修改" + len + "行。");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, ps);
        }
        return len;
    }

    /**
     * 通用的,不带参数的查询
     *
     * @param sql 执行的sql语句
     * @param rsh ResultSetHandler接口的实现子类 new BeanHandler<Account>(Account.class)
     *            或new BeanListHandler<Account>(Account.class)
     * @return 返回的查询对象或对象集合，可以使用List<Account> list=接收或Account account接收
     * @throws SQLException
     */
    public static <T> T query(String sql, ResultSetHandler<T> rsh){
        DataSource dataSource = null;
        QueryRunner queryRunner = null;
        T t = null;
        try {
            dataSource = JdbcUtils.getDataSource();
            queryRunner = new QueryRunner(dataSource);
            t = queryRunner.query(sql, rsh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 带参数的通用查询
     *
     * @param sql  执行的sql语句
     * @param rsh  ResultSetHandler接口的实现子类 new BeanHandler<Account>(Account.class)
     *             或new BeanListHandler<Account>(Account.class)
     * @param args sql语句参数
     * @return 返回的查询对象或对象集合，可以使用List<Account> list=接收或Account account接收
     */
    public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... args) {
        T t = null;
        try {
            DataSource dataSource = JdbcUtils.getDataSource();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            t = queryRunner.query(sql, rsh, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


}
