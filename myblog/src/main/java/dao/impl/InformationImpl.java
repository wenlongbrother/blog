package dao.impl;

import dao.InformationDao;
import domain.po.Information;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.CRUDUtils;
import util.MD5;

import java.awt.image.CropImageFilter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
public class InformationImpl implements InformationDao {
    private Date date = new Date();
    private String sql;

    @Override
    public void register(String account, String userName, String passWord, String email,String passwordWithSalt,String salt) throws Exception {
        sql = "INSERT INTO information (create_time,update_time,account,user_name,password,salt,email)VALUES(?,?,?,?,?,?,?)";
        CRUDUtils.update(sql, date, date, account, userName, passwordWithSalt, salt, email);
    }

    public boolean isRegister(String account) {
        sql = "SELECT * FROM information WHERE account = ?";
        BeanListHandler<Information> beanHandler = new BeanListHandler<>(Information.class);
        List<Information> information = CRUDUtils.query(sql, beanHandler, account);
        if (information.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Information login(String account, String passWord)throws Exception {
        sql = "SELECT * FROM information WHERE account = ?";
        BeanHandler<Information>beanHandler = new BeanHandler<>(Information.class);
        Information information = CRUDUtils.query(sql,beanHandler,account);
        return information;
    }

    @Override
    public Information checkCookie(String uid) throws Exception {
        sql = "SELECT * FROM information WHERE id = ?";
        BeanHandler<Information>beanHandler = new BeanHandler<>(Information.class);
        Information information = CRUDUtils.query(sql,beanHandler,uid);
        return information;
    }
}
