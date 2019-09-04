package dao;

import controller.VerifyCodeServlet;
import dao.impl.ClassificationNameImpl;
import domain.po.ClassificationName;

import java.util.List;

/**
 * @author Administrator
 */
public interface ClassificationNameDao {
    /**
     * 增加分类接口
     * @param classificationName
     * @throws Exception
     */
    void insertClassification(String classificationName)throws Exception;

    /**
     * 查询所有分类接口
     * @return
     * @throws Exception
     */
    List<ClassificationName>queryAllClassificationName()throws Exception;

    /**
     * 修改分类名称接口
     * @param classificationName
     * @param newClassificationName
     * @throws Exception
     */
    void updateClassificationName(String classificationName,String newClassificationName)throws Exception;

    /**
     * 删除分类接口
     * @param classificationName
     * @throws Exception
     */
    void deleteClassificationName(String classificationName)throws Exception;
}
