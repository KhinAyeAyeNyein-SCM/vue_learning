package medium.vue.api.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import medium.vue.api.persistence.dao.UserDAO;
import medium.vue.api.persistence.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SessionFactory session;
    
    private static final String SELECT_USER_HQL = "SELECT " 
                                                + "u "
                                                + "FROM "
                                                + "User u ";

    
    /**
     * <h2> dbSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return void
     */
    @Override
    public void dbSaveUser(User user) {
        this.session.getCurrentSession().save(user);
    }

    /**
     * <h2> dbGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return Users
     */
    @SuppressWarnings("rawtypes")
    @Override
    public User dbGetUserById(Integer userId) {
        StringBuffer queryBuffer = new StringBuffer(SELECT_USER_HQL);
        queryBuffer.append("WHERE u.id = :id ");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        query.setParameter("id", userId);
        return (User) query.uniqueResult();
    }
    
    /**
     * <h2> dbGetUserByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param email
     * @return
     * @return Users
     */
    @SuppressWarnings("rawtypes")
    @Override
    public User dbGetUserByEmail(String email) {
        StringBuffer queryBuffer = new StringBuffer(SELECT_USER_HQL);
        queryBuffer.append("WHERE u.email = :email ");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }
    
    @Override
    public void dbUpdateUser(User user) {
        this.session.getCurrentSession().saveOrUpdate(user);
        this.session.getCurrentSession().flush();
    }
}
