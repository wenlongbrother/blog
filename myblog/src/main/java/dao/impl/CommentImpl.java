package dao.impl;

import dao.CommentDao;
import domain.po.Comment;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.CRUDUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentImpl implements CommentDao {
    private String sql;
    private Date date = new Date();
    @Override
    public void insertComment(int userId, int articleId, String content) throws Exception{
        sql = "INSERT INTO comment(create_time,update_time,content,user_id,article_id)VALUES(?,?,?,?,?)";
        CRUDUtils.update(sql,date,date,content,userId,articleId);
    }

    @Override
    public void deleteComment(int commentId) throws Exception {
        sql = "UPDATE comment SET deleted=1 WHERE id = ?";
        CRUDUtils.update(sql,commentId);
    }

    @Override
    public List<Comment> queryComment(int articleId, int page) throws Exception {
        int pageLow = 5*(page-1);
        int pageLimit = 5*page;
        sql = "SELECT id, create_time as createTime, update_time as updateTime,deleted,content,user_id as userId,article_Id as articleId FROM comment WHERE article_id = ? ORDER BY update_time LIMIT ?,? ";
        BeanListHandler<Comment>beanListHandler = new BeanListHandler<>(Comment.class);
        List<Comment> list = CRUDUtils.query(sql,beanListHandler,articleId,pageLow,pageLimit);
        List<Comment> list1 = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
            if(list.get(i).getDeleted()!=1){
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
