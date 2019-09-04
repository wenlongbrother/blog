package dao.impl;

import dao.ClassificationNameDao;
import domain.po.ClassificationName;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.CRUDUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class ClassificationNameImpl implements ClassificationNameDao {
    private String sql;
    private Date date= new Date();
    List<ClassificationName>list1 = new ArrayList<>();
    @Override
    public void insertClassification(String classificationName)throws Exception{
        sql = "INSERT INTO classification_name(create_time,update_time,classification_name)VALUES(?,?,?)";
        CRUDUtils.update(sql,date,date,classificationName);
    }

    @Override
    public List<ClassificationName> queryAllClassificationName() throws Exception {
        sql = "SELECT id,deleted,create_time as createTime,update_time as updateTime,classification_name as classificationName FROM classification_name";
        BeanListHandler<ClassificationName>beanListHandler = new BeanListHandler<>(ClassificationName.class);
        List<ClassificationName>list = CRUDUtils.query(sql,beanListHandler);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDeleted()!=1){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void updateClassificationName(String classificationName,String newClassificationName) throws Exception {
        sql = "UPDATE classification_name SET classification_name=? WHERE classification_name = ?";
        CRUDUtils.update(sql,newClassificationName,classificationName);
    }

    @Override
    public void deleteClassificationName(String classificationName) throws Exception {
        sql = "UPDATE classification_name SET deleted = 1 WHERE classification_name = ?";
        CRUDUtils.update(sql,classificationName);
    }
}
