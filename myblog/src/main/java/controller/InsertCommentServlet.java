package controller;

import com.alibaba.fastjson.JSONObject;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentService commentService = new CommentService();
        String userId = request.getParameter("userId");
        String articleId = request.getParameter("articleId");
        String content = request.getParameter("content");
        try {
            JSONObject jsonObject = new JSONObject();
            commentService.insertComment(Integer.parseInt(userId),Integer.parseInt(articleId),content);
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
            jsonObject.put("data",null);
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
