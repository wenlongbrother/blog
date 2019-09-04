package controller;

import com.alibaba.fastjson.JSONObject;
import service.ArticleService;
import util.permission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class InsertArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        try {
            if (permission.permission(Integer.parseInt(userId)) == 0) {
                throw new RuntimeException("对不起权限不够");
            } else {
                    JSONObject jsonObject = new JSONObject();
                    ArticleService articleInserService = new ArticleService();
                    articleInserService.insertArticle(Integer.parseInt(userId), content, title);
                    jsonObject.put("msg", "success");
                    jsonObject.put("code", 0);
                    jsonObject.put("data", null);
                    response.getWriter().write(JSONObject.toJSONString(jsonObject));
            }
        }catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("msg", "fail");
            jsonObject.put("code", 1);
            jsonObject.put("error", "对不起,权限不够");
            response.getWriter().write(JSONObject.toJSONString(jsonObject));
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
