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
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        String uid = request.getParameter("uid");
        ArticleService articleService = new ArticleService();
        JSONObject jsonObject = new JSONObject();
        try {
        if(permission.permission(Integer.parseInt(uid))==0){
            throw new RuntimeException("对不起权限不够");
        }
            else {
            articleService.deleteArticle(Integer.parseInt(articleId));
            jsonObject.put("msg","success");
            jsonObject.put("code",0);
            jsonObject.put("data",null);
            response.getWriter().write(JSONObject.toJSONString(jsonObject));
        }
        } catch (Exception e) {
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
