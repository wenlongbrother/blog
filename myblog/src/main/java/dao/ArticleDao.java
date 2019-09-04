package dao;

import domain.po.Article;

import java.util.List;

/**
 * @author Administrator
 */
public interface ArticleDao {

    /**
     * 增加文章
     * @param userId
     * @param content
     * @param title
     * @throws Exception
     */
    void insertArticle(int userId,String content,String title)throws Exception;

    /**
     * 查询文章
     * @param articleId
     */
    Article queryArticle(int articleId)throws Exception;
    /**
     * 查询所有文章
     * @param page
     * @return
     * @throws Exception
     */
    List<Article> findAllArticle(int page)throws Exception;

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    List<Article>finArticleByKeyWord(String keyword)throws Exception;

    /**
     * 删除文章
     * @param articleTitle
     * @throws Exception
     */
    void deleteArticle(int articleTitle)throws Exception;

    /**
     * 增加点赞数
     * @param articleId
     * @throws Exception
     */
    void insertPraise(int articleId)throws Exception;

    /**
     * 取消点赞
     * @param articleId
     * @throws Exception
     */
    void deletePraise(int articleId)throws Exception;
}
