package controller;

import com.alibaba.fastjson.JSONObject;
import domain.po.Information;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Objects;

/**
 * 登录servlet
 * @author Administrator
 */
public class LoginServlet extends HttpServlet {
    private String safeCode;
    private String verifyCode;
    String account;
    String passWord;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                account = request.getParameter("account");
                passWord = request.getParameter("passWord");
                verifyCode = request.getParameter("VerifyCode");
                safeCode = (String) request.getSession().getAttribute("VerifyCode");
                if(!verifyCode.equals(safeCode)){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code",1);
                    jsonObject.put("msg","fail");
                    jsonObject.put("error","对不起验证码错误");
                    response.getWriter().write(jsonObject.toJSONString());
                    return;
                }else {
                    LoginService loginService = new LoginService();
                    Information information = loginService.login(account, passWord);
                    if (loginService.isLogin(account, passWord, information)) {
                        Cookie cookie = new Cookie("uid",information.getId()+"");
                        cookie.setMaxAge(30*60);
                        cookie.setPath("../blog/article/all");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("code", 0);
                        jsonObject.put("msg", "success");
                        jsonObject.put("data", information);
                        response.getWriter().write(jsonObject.toJSONString());
                        response.addCookie(cookie);
                    } else {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("code", 1);
                        jsonObject.put("msg", "fail");
                        jsonObject.put("error", "对不起,账号或密码错误");
                        response.getWriter().write(jsonObject.toJSONString());
                    }
                }
            }
        catch (Exception e){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 1);
                jsonObject.put("msg", "fail");
                jsonObject.put("error", "对不起,请求错误");
                response.getWriter().write(jsonObject.toJSONString());
                e.printStackTrace();

    }

}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginServlet that = (LoginServlet) o;
        return
                Objects.equals(safeCode, that.safeCode) &&
                Objects.equals(verifyCode, that.verifyCode);
    }


}
