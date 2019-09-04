package dao;

import domain.po.Comment;

import java.util.List;

/**
 * 评论接口
 * @author Administrator
 */
public interface CommentDao {
    /**
     * 增加评论接口
     * @param userId
     * @param articleId
     * @param content
     * @throws Exception
     */
    void insertComment(int userId,int articleId, String content)throws Exception;

    /**
     * 删除评论接口
     * @param commentId
     * @throws Exception
     */
    void deleteComment(int commentId)throws Exception;

    /**
     * 查询评论接口
     * @param articleId
     * @param page
     * @return
     * @throws Exception
     */
    List<Comment> queryComment(int articleId, int page)throws Exception;
}
