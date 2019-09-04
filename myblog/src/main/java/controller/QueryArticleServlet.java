package controller;

import com.alibaba.fastjson.JSONObject;
import domain.po.Article;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class QueryArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        ArticleService articleService = new ArticleService();
        try {
            JSONObject jsonObject = new JSONObject();
            Article article;
            article = articleService.queryArticle(Integer.parseInt(articleId));
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
            jsonObject.put("data",article);
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            e.printStackTrace();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("data","对不起，出现错误");
            response.getWriter().write(jsonObject.toJSONString());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
