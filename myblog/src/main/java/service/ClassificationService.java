package service;

import dao.impl.ClassificationImpl;
import dao.impl.ClassificationNameImpl;
import domain.po.Classification;
import domain.po.ClassificationName;

import java.util.List;

/**
 * @author Administrator
 */
public class ClassificationService {
    ClassificationNameImpl classificationImpl = new ClassificationNameImpl();
    ClassificationImpl classification = new ClassificationImpl();

    /**
     * 增加分类功能
     * @param classificationName
     */
    public void insertClassification(String classificationName)throws Exception{
        classificationImpl.insertClassification(classificationName);
    }

    /**
     * 查询所有分类
     * @return
     * @throws Exception
     */
    public List<ClassificationName>queryAllClassificationName()throws Exception{
        List<ClassificationName>list = classificationImpl.queryAllClassificationName();
        return list;
    }

    /**
     * 修改分类名称
     * @param classificationName
     * @param newClassificationName
     * @throws Exception
     */
    public void updateClassificationName(String classificationName,String newClassificationName)throws Exception{
        classificationImpl.updateClassificationName(classificationName,newClassificationName);
    }

    /**
     * 删除分类
     * @param classificationName
     * @throws Exception
     */
    public void deleteClassificationName(String classificationName)throws Exception{
        classificationImpl.deleteClassificationName(classificationName);
    }

    /**
     * 增加文章进分类
     * @param articleId
     * @param classificationId
     * @throws Exception
     */
    public void insertIntoClassification(String articleId, String classificationId)throws Exception{
        classification.insertIntoClassification(articleId,classificationId);
    }

    public void  deleteIntoClassification(String classificationId)throws Exception{
        classification.deleteIntoClassification(classificationId);
    }

}
