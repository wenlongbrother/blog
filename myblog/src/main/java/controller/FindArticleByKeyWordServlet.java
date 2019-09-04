package controller;

import com.alibaba.fastjson.JSONObject;
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
public class FindArticleByKeyWordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("keyWord");
        ArticleService articleService = new ArticleService();
        List<Article> list;
        JSONObject jsonObject = new JSONObject();
        try {
            list= articleService.finArticleByKeyWord(keyWord);
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
            jsonObject.put("data",list);
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("data","对不起，出现错误");
            response.getWriter().write(jsonObject.toJSONString());
        }
    }
}
