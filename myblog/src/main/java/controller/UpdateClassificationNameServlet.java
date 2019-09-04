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
public class UpdateClassificationNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classificationName = request.getParameter("classificationName");
        String newClassificationName = request.getParameter("newClassificationName");
        ClassificationService classificationService = new ClassificationService();
        JSONObject jsonObject = new JSONObject();
        String uid = request.getParameter("uid");
        try {
            if (permission.permission(Integer.parseInt(uid)) == 0) {
                throw new RuntimeException("对不起权限不够");
            } else {
                classificationService.updateClassificationName(classificationName, newClassificationName);
                jsonObject.put("msg", "success");
                jsonObject.put("code", 0);
                jsonObject.put("data", null);
                response.getWriter().write(JSONObject.toJSONString(jsonObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg", "fail");
            jsonObject.put("code", 1);
            jsonObject.put("error",e.getMessage());
            response.getWriter().write(JSONObject.toJSONString(jsonObject));
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
