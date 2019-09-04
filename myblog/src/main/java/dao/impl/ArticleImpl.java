package dao.impl;

import dao.ArticleDao;
import domain.po.Article;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.CRUDUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class ArticleImpl implements ArticleDao {
    private String sql;
    private Date date = new Date();

    @Override
    public void insertArticle( int userId,String content,String title) throws Exception{
        sql = "INSERT INTO article (create_time,update_time,user_id,content,title)VALUES(?,?,?,?,?)";
        CRUDUtils.update(sql,date,date,userId,content,title);
    }

    @Override
    public List<Article> findAllArticle(int page)throws Exception {
        int pageLow = 5*(page-1);
        int pageLimit = 5*page;
        sql = "SELECT id,create_time as createTime,update_time as updateTime,user_id as userId,content,title,praise,deleted FROM article ORDER BY update_time LIMIT ?,?";
        BeanListHandler<Article> beanListHandler = new BeanListHandler<>(Article.class);
        List<Article> list = CRUDUtils.query(sql,beanListHandler,pageLow,pageLimit);
        List<Article> list1 = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            if(list.get(i).getDeleted()!=1){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public List<Article> finArticleByKeyWord(String keyword) throws Exception {
        sql = "SELECT id,create_time as createTime,update_time as updateTime,user_id as userId,content,title,praise,deleted FROM article WHERE content LIKE ?";
        BeanListHandler<Article>beanListHandler = new BeanListHandler<>(Article.class);
        List<Article>list = CRUDUtils.query(sql,beanListHandler,"%"+keyword+"%");
        List<Article>list1 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDeleted()!=1){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void deleteArticle(int articleTitle)throws Exception {
        sql = "UPDATE article SET deleted=1 WHERE id=?";
        CRUDUtils.update(sql,articleTitle);
    }

    @Override
    public Article queryArticle(int articleId) throws Exception{
        sql = "SELECT id, create_time AS createTime, update_time AS updateTime,deleted,user_id AS userId,content,title,praise FROM article WHERE id = ?";
        Article article ;
        BeanHandler<Article>beanHandler = new BeanHandler<>(Article.class);
        article = CRUDUtils.query(sql,beanHandler,articleId);
        if (article.getDeleted()!=1) {
            return article;
        }else {
            throw new RuntimeException("对不起，此文章已被删除");
        }
    }

    @Override
    public void insertPraise(int articleId) throws Exception {
        sql = "UPDATE article SET praise = praise+1 WHERE id = ?";
        CRUDUtils.update(sql,articleId);
    }

    @Override
    public void deletePraise(int articleId) throws Exception {
        sql = "UPDATE article SET praise = praise-1 WHERE id = ?";
        CRUDUtils.update(sql,articleId);
    }
}
