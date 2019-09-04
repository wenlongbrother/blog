package service;

import dao.impl.InformationImpl;
import domain.po.Information;
import util.MD5;

import java.io.Serializable;
import java.util.Objects;

public class LoginService implements Serializable {
    InformationImpl informationImpl = new InformationImpl();
    Information information = new Information();
    String newSaltAndPassWord;
    String saltAndPassWord;

    /**
     * 登录service
     * @param account
     * @param passWord
     */
    public Information login(String account,String passWord)throws Exception{
        information = informationImpl.login(account,passWord);
        return information;
    }
    public boolean isLogin(String account,String passWord,Information information)throws Exception{
        MD5 md5 = new MD5();
        //登录请求的密码加盐
        newSaltAndPassWord = md5.Md5WithSalt(account,passWord,1)+md5.getSalt(account);
        //数据库的密码加盐
        saltAndPassWord = information.getPassWord()+information.getSalt();
        if(newSaltAndPassWord.equals(saltAndPassWord)){
            return true;
        }else {
            return false;
        }
    }
    public Information checkUid(String uid)throws Exception{
        InformationImpl information = new InformationImpl();
        return information.checkCookie(uid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginService that = (LoginService) o;
        return Objects.equals(informationImpl, that.informationImpl) &&
                Objects.equals(information, that.information) &&
                Objects.equals(newSaltAndPassWord, that.newSaltAndPassWord) &&
                Objects.equals(saltAndPassWord, that.saltAndPassWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(informationImpl, information, newSaltAndPassWord, saltAndPassWord);
    }
}
