package service;

import dao.impl.ArticleImpl;
import domain.po.Article;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class ArticleService implements Serializable {
    ArticleImpl article = new ArticleImpl();

    public void insertArticle(int userId, String content, String title) throws Exception {
        article = new ArticleImpl();
        article.insertArticle(userId, content, title);
    }

    public List queryAllArticle(int pages) throws Exception {
        article = new ArticleImpl();
        List<Article> list = article.findAllArticle(pages);
        return list;
    }

    public List finArticleByKeyWord(String keyword) throws Exception {
        article = new ArticleImpl();
        List<Article> list = article.finArticleByKeyWord(keyword);
        return list;
    }

    public void deleteArticle(int articleTitle) throws Exception {
        article = new ArticleImpl();
        article.deleteArticle(articleTitle);
    }

    public Article queryArticle(int articleId) throws Exception {
        article = new ArticleImpl();
        Article article1;
        article1 = article.queryArticle(articleId);
        return article1;
    }

    public void insertPraise(int articleId) throws Exception {
        article = new ArticleImpl();
        article.insertPraise(articleId);
    }

    public void deletePraise(int articleId) throws Exception {
        article = new ArticleImpl();
        article.deletePraise(articleId);
    }
}
