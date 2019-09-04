package dao.impl;

import dao.ClassificationDao;
import util.CRUDUtils;

import java.util.Date;

public class ClassificationImpl implements ClassificationDao {
    private String sql;
    private Date date = new Date();
    @Override
    public void insertIntoClassification(String articleId, String classificationId)throws Exception {
        sql = "INSERT INTO classification(create_time,update_time,article_id,classification_name_id)VALUES(?,?,?,?)";
        CRUDUtils.update(sql,date,date,articleId,classificationId);
    }

    @Override
    public void deleteIntoClassification(String classificationId)throws Exception{
        sql = "UPDATE classification SET deleted = 1 WHERE id = ?";
        CRUDUtils.update(sql,classificationId);
    }
}
