package util;

import domain.po.Information;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * fruit_MD5类的以下方法功能：
 * String Md5WithSalt(String username,String password, int type)
 * 提供用户输入的用户名、密码和登录/注册类型【1/0】返回加盐后的密码
 * String salt() 随机生成盐
 * String byteToArrayStr(byte[] bytes)
 * 将字节数组转换成十六进制字符串
 * String getSaltFromPassword(String passwordWinthSalt)
 * 从数据库中查找到的带盐密码中提取出salt
 * String getSalt(String username)
 * 根据用户名在数据库中查找并返回用户的盐
 */
public class MD5 {
    private  final char[] array = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public MD5(){
    }
    /**
     * 提供用户输入的用户名、密码和登录/注册类型返回加盐密码
     * @author 闫娟
     * @param username 用户输入的用户名
     * @param password 用户输入的密码
     * @param type ‘0’注册，‘1’登录
     * @return MD5加密后的字符串（加盐密码）
     */
    public  String Md5WithSalt(String username,String password, int type) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String salt = null;
            if(type == 0){
                salt = salt();
            }
            else if(type == 1){
                salt = getSalt(username);
            }
            //加盐
            String inputWithSalt = password + salt;
            //加盐后转换输出
            String saltResult = byteToArrayStr(md.digest(inputWithSalt.getBytes()));
            char[] cs = new char[48];
            for(int i = 0; i < 48 ; i += 3) {
                cs[i] = saltResult.charAt(i / 6);
                if (salt != null) {
                    cs[i + 1] = salt.charAt(i / 3);
                }
                cs[i + 2] = saltResult.charAt(i / 7);
            }
            saltResult = new String(cs);
            return saltResult;

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }
    /**
     * 功能： 定义简单生成盐
     * @return 返回一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     * */

    private  String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for(int i = 0 ; i < sb.capacity(); i++){
            sb.append(array[random.nextInt(16)]);
        }
        return sb.toString();
    }
    /**
     * 将字节数组转换成十六进制字符串
     * @return 一个十六进制字符串
     * */
    private  String byteToArrayStr(byte[] bytes) {
        int len = bytes.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            result.append(array[byte0 >>> 4 & 0xf]);
            result.append(array[byte0 & 0xf]);
        }
        return result.toString();
    }
    /**
     * 获得salt
     * @return 提取的salt --字符串
     */
    public String getSaltFromPassword(String passwordWithSalt) {
        StringBuilder sb = new StringBuilder();
        char[] h = passwordWithSalt.toCharArray();
        for (int i = 0; i < h.length; i += 3) {
            sb.append(h[i + 1]);
        }
        return sb.toString();
    }
    /**
     * 根据用户名在数据库中查找并返回用户的盐
     * @param username 用户名
     * @return 用户注册时随机生成的盐
     */
    public  String getSalt(String username){
        String sql = "select salt from Information where account = ?";
        BeanHandler<Information>beanHandler = new BeanHandler<>(Information.class);
        Information information = CRUDUtils.query(sql,beanHandler,username);
        return information.getSalt();
    }
}
