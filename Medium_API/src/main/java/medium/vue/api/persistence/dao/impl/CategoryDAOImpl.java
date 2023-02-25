package medium.vue.api.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import medium.vue.api.persistence.dao.CategoryDAO;
import medium.vue.api.persistence.entity.Category;

/**
 * <h2> CategoryDAOImpl Class</h2>
 * <p>
 * Process for Displaying CategoryDAOImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    /**
     * <h2> session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private SessionFactory session;
    
    /**
     * <h2> SELECT_CATEGORY_HQL</h2>
     * <p>
     * SELECT_CATEGORY_HQL
     * </p>
     */
    private static final String SELECT_CATEGORY_HQL = "SELECT c "
                                                    + "FROM Category c ";
    
    /**
     * <h2> dbGetCategoryList </h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Category> dbGetCategoryList() {
        Query query = this.session.getCurrentSession().createQuery(SELECT_CATEGORY_HQL);
        return query.getResultList();
    }
    
    /**
     * <h2> dbSaveCategory </h2>
     * <p>
     * 
     * </p>
     * 
     * @param category
     */
    @Override
    public void dbSaveCategory(Category category) {
        this.session.getCurrentSession().persist(category);
    }
    
    /**
     * <h2> dbGetCategoryByName </h2>
     * <p>
     * 
     * </p>
     * 
     * @param categoryName
     * @return
     */
    @Override
    public Category dbGetCategoryByName(String categoryName) {
        StringBuffer queryBuffer = new StringBuffer(SELECT_CATEGORY_HQL);
        queryBuffer.append("WHERE c.name = :cateName");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        query.setParameter("cateName", categoryName);
        return (Category) query.getSingleResult();
    }
}
