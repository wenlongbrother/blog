package service;

import dao.impl.CommentImpl;
import domain.po.Comment;

import java.util.List;

/**
 * @author Administrator
 */
public class CommentService {
    CommentImpl commentImpl = new CommentImpl();

    /**
     * 增加评论
     * @param userId
     * @param articleId
     * @param content
     * @throws Exception
     */
    public void insertComment(int userId,int articleId, String content)throws Exception{
        commentImpl.insertComment(userId,articleId,content);
    }

    /**
     * 删除评论
     * @param commentId
     * @throws Exception
     */
    public void deleteComment(int commentId)throws Exception{
        commentImpl.deleteComment(commentId);
    }
    public List<Comment>queryComment(int articleId, int page)throws Exception{
        List<Comment>list = commentImpl.queryComment(articleId,page);
        return list;
    }
}
