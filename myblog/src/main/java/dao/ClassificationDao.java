package dao;

/**
 * 分了详情接口
 * @author Administrator
 */
public interface ClassificationDao {
    /**
     * 增加文章进入分类
     * @param articleId
     * @param classificationId
     * @throws Exception
     */
    void insertIntoClassification(String articleId,String classificationId) throws Exception;

    /**
     * 移除分类里面的文章
     * @param classificationId
     * @throws Exception
     */
    void deleteIntoClassification(String classificationId)throws Exception;

}
