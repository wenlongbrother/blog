package controller;

import com.alibaba.fastjson.JSONObject;
import dao.impl.ArticleImpl;
import domain.po.Article;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public class QueryAllArticleServlet extends HttpServlet {
    private JSONObject jsonObject = new JSONObject();
    private int pages;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String page = request.getParameter("page");
            if(page == null){
                pages=1;
            }
            else {
                pages = Integer.parseInt(page);
            }
            ArticleService article = new ArticleService();
            List<Article>list = article.queryAllArticle(pages);
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
            jsonObject.put("data",list);
            response.getWriter().write(jsonObject.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("error","对不起，出现错误");
            response.getWriter().write(jsonObject.toJSONString());
        }
    }
}
