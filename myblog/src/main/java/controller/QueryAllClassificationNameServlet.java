package controller;

import com.alibaba.fastjson.JSONObject;
import domain.po.ClassificationName;
import service.ClassificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public class QueryAllClassificationNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassificationService classificationService = new ClassificationService();
        JSONObject jsonObject = new JSONObject();
        try {
            List<ClassificationName>list = classificationService.queryAllClassificationName();
            jsonObject.put("msg","success");
            jsonObject.put("code",0);
            jsonObject.put("data",list);
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("error","对不起，出现错误");
            response.getWriter().write(jsonObject.toJSONString());
        }
    }
}
