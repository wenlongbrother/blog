package controller;

import com.alibaba.fastjson.JSONObject;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class InsertPraiseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        ArticleService articleService = new ArticleService();
        try {
            JSONObject jsonObject = new JSONObject();
            articleService.insertPraise(Integer.parseInt(articleId));
            jsonObject.put("msg","success");
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            response.getWriter().write(JSONObject.toJSONString(jsonObject));
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("msg","fail");
            jsonObject.put("code",1);
            jsonObject.put("error",e.getMessage());
            response.getWriter().write(JSONObject.toJSONString(jsonObject));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
