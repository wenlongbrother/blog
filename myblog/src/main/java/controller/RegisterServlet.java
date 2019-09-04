package controller;

import com.alibaba.fastjson.JSONObject;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Administrator
 */
public class RegisterServlet extends HttpServlet {
    private JSONObject jsonObject = new JSONObject();
    private String safeCode;
    private String verifyCode;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String email = request.getParameter("email");
        verifyCode = request.getParameter("verifyCode");
        safeCode = (String) request.getSession().getAttribute("VerifyCode");
        System.out.println(verifyCode);
        System.out.println(safeCode);
//        if(!safeCode.equals(verifyCode)){
//            jsonObject.put("code",1);
//            jsonObject.put("msg","fail");
//            jsonObject.put("error","对不起验证码错误");
//            response.getWriter().write(jsonObject.toJSONString());
//            return;
//        }else {
            try {
                RegisterService registerService = new RegisterService();
                registerService.register(account,userName,passWord,email);
                jsonObject.put("code",0);
                jsonObject.put("msg","success");
                jsonObject.put("data",null);
                response.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("code",1);
                jsonObject.put("msg","fail");
                jsonObject.put("error","对不起,账号已存在");
                response.getWriter().write(jsonObject.toJSONString());
            }
        }
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterServlet that = (RegisterServlet) o;
        return Objects.equals(jsonObject, that.jsonObject) &&
                Objects.equals(safeCode, that.safeCode) &&
                Objects.equals(verifyCode, that.verifyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(safeCode, verifyCode);
    }
}
