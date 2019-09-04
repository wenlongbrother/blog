package controller;

import com.alibaba.fastjson.JSONObject;
import service.ClassificationService;
import util.permission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class InsertClassificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classificationName = request.getParameter("classificationName");
        ClassificationService classificationService = new ClassificationService();
        String uid = request.getParameter("uid");
        try {
            if (permission.permission(Integer.parseInt(uid)) == 0) {
                throw new RuntimeException("对不起权限不够");
            }
            else {
                JSONObject jsonObject = new JSONObject();
                classificationService.insertClassification(classificationName);
                jsonObject.put("code", 0);
                jsonObject.put("msg", "success");
                jsonObject.put("data", null);
                response.getWriter().write(jsonObject.toJSONString());
            }
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("code", 1);
            jsonObject.put("msg", "fail");
            jsonObject.put("error", e.getMessage());
            response.getWriter().write(jsonObject.toJSONString());
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
