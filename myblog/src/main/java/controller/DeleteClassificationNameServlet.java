package controller;

import com.alibaba.fastjson.JSONObject;
import service.ArticleService;
import service.ClassificationService;
import util.permission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteClassificationNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classificationName = request.getParameter("classificationName");
        ClassificationService classificationService = new ClassificationService();
        JSONObject jsonObject = new JSONObject();
        String uid = request.getParameter("uid");
        try {
            if(permission.permission(Integer.parseInt(uid))==0) {
                throw new RuntimeException("对不起权限不够");
            }
            else {
                classificationService.deleteClassificationName(classificationName);
                jsonObject.put("code",0);
                jsonObject.put("msg","success");
                jsonObject.put("data",null);
                response.getWriter().write(jsonObject.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("error",e.getMessage());
            response.getWriter().write(jsonObject.toJSONString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
