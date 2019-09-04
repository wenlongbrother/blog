package controller;

import com.alibaba.fastjson.JSONObject;
import domain.po.Comment;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QueryCommentServlet extends HttpServlet {
    private int pages;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentService commentService = new CommentService();
        String articleId = request.getParameter("articleId");
        String page = request.getParameter("page");
        if(page == null){
            pages=1;
        }
        else {
            pages = Integer.parseInt(page);
        }
        try {
            JSONObject jsonObject = new JSONObject();
            List<Comment>list = commentService.queryComment(Integer.parseInt(articleId),pages);
            jsonObject.put("code",0);
            jsonObject.put("msg","success");
            jsonObject.put("data",list);
            response.getWriter().write(jsonObject.toJSONString());
        }catch (NullPointerException e){
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("error",e.getMessage());
            response.getWriter().write(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",1);
            jsonObject.put("msg","fail");
            jsonObject.put("error","对不起，出现错误");
            response.getWriter().write(jsonObject.toJSONString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
