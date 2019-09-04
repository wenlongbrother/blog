package dao;

import domain.po.Information;

public interface InformationDao {
    /**
     * 注册功能
     * @param account
     * @param userName
     * @param passWord
     * @param email
     * @throws Exception
     */
    void register(String account, String userName, String passWord,String email,String passwordWithSalt,String salt) throws Exception;

    /**
     * 登录功能
     * @param account
     * @param passWord
     */
    Information login(String account, String passWord) throws Exception;

    Information checkCookie(String uid)throws Exception;

}
