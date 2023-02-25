package medium.vue.api.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import medium.vue.api.persistence.dao.PostDAO;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.web.form.PostForm;

/**
 * <h2> PostDaoImpl Class</h2>
 * <p>
 * Process for Displaying PostDaoImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Repository
public class PostDaoImpl implements PostDAO{
    /**
     * <h2> session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private SessionFactory session;
    
    /**
     * <h2> POST_LIST_HQL</h2>
     * <p>
     * POST_LIST_HQL
     * </p>
     */
    public static String POST_LIST_HQL = "SELECT p "
                                        + "FROM Post p "
                                        + "WHERE p.deletedAt "
                                        + "IS NULL ";
    
    /**
     * <h2> dbSavePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return void
     */
    @Override
    public void dbSavePost(Post post) {
        this.session.getCurrentSession().persist(post);
    }
    
    /**
     * <h2> dbGetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return
     * @return List<Posts>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Post> dbGetPostList(PostForm post) {
        StringBuffer queryBuffer = new StringBuffer(POST_LIST_HQL);
        queryBuffer.append("");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        return query.getResultList();
    }
    
    /**
     * <h2> dbGetPostById</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return
     * @return Posts
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Post dbGetPostById(Integer postId) {
        StringBuffer queryBuffer = new StringBuffer(POST_LIST_HQL);
        queryBuffer.append("AND p.id = :postId");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        query.setParameter("postId", postId);
        return (Post) query.uniqueResult();
    }
    
    /**
     * <h2> dbUpdatePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return void
     */
    @Override
    public void dbUpdatePost(Post post) {
        this.session.getCurrentSession().saveOrUpdate(post);
        this.session.getCurrentSession().flush();
    }
}