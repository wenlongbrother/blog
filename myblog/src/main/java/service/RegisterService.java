package service;

import dao.impl.InformationImpl;
import util.MD5;

import java.io.Serializable;

public class RegisterService implements Serializable {

    public void register(String account, String userName, String passWord,String email) throws Exception {
        InformationImpl information = new InformationImpl();
        if (information.isRegister(account) == true) {
            MD5 md5 = new MD5();
            String passwordWithSalt = md5.Md5WithSalt(account, passWord, 0);
            String salt = md5.getSaltFromPassword(passwordWithSalt);
            information.register(account,userName,passWord,email,passwordWithSalt,salt);
        }
        else {
            throw new RuntimeException("对不起,账号存在");
        }
    }
}
