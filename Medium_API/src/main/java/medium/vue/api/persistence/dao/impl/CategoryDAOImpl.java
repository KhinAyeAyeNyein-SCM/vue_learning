package medium.vue.api.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import medium.vue.api.persistence.dao.CategoryDAO;
import medium.vue.api.persistence.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    private SessionFactory session;
    
    private static final String SELECT_CATEGORY_HQL = "SELECT c "
                                                    + "FROM Category c ";
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Category> dbGetCategoryList() {
        Query query = this.session.getCurrentSession().createQuery(SELECT_CATEGORY_HQL);
        return query.getResultList();
    }
    
    @Override
    public void dbSaveCategory(Category category) {
        this.session.getCurrentSession().persist(category);
    }
}
