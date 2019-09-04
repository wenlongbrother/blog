package util;

import domain.po.Information;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author Administrator
 */
public class permission {
    public static int permission(int uid){
        String sql = "SELECT permission FROM information WHERE id=?";
        BeanHandler<Information>beanHandler = new BeanHandler<>(Information.class);
        Information permission = CRUDUtils.query(sql,beanHandler,uid);
        return permission.getPermission();
    }
}
