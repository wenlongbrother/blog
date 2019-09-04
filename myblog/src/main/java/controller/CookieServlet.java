package controller;

import com.alibaba.fastjson.JSONObject;
import domain.po.Information;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;

/**
 * @author Administrator
 *
 */
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[]cks = request.getCookies();
        JSONObject jsonObject = new JSONObject();
        try {
            if(cks!=null){
                String uid = null;
                //获取cookie信息
                for(Cookie c:cks){
                    if("uid".equals(c.getName())){
                        uid=c.getValue();
                    }
                    if("".equals(uid)){
                        request.getRequestDispatcher("LoginServlet").forward(request,response);
                        return;
                    }else {
                        //校验UID用户信息
                        //获取业务层对象
                        LoginService loginService = new LoginService();
                        Information information = loginService.checkUid(uid);
                        if(information!=null){
                            response.sendRedirect("../blog/article/all");
                            return;
                        }
                    }
                }
            }else{
                request.getRequestDispatcher("../blog/login").forward(request,response);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code", 1);
            jsonObject.put("msg", "fail");
            jsonObject.put("error", "对不起,出现异常");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
