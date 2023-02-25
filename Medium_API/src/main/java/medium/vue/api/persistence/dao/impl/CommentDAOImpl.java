package medium.vue.api.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import medium.vue.api.persistence.dao.CommentDAO;
import medium.vue.api.persistence.entity.Comment;
import medium.vue.api.persistence.entity.Post;

/**
 * <h2> CommentDAOImpl Class</h2>
 * <p>
 * Process for Displaying CommentDAOImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Repository
public class CommentDAOImpl implements CommentDAO{
    /**
     * <h2> session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private SessionFactory session;
    
    /**
     * <h2> SELECT_COMMENT_HQL</h2>
     * <p>
     * SELECT_COMMENT_HQL
     * </p>
     */
    private static final String SELECT_COMMENT_HQL = "SELECT c "
                                                    + "FROM Comment c ";
    
    /**
     * <h2> dbSaveComment</h2>
     * <p>
     * 
     * </p>
     *
     * @param comment
     * @return void
     */
    @Override
    public void dbSaveComment(Comment comment) {
        this.session.getCurrentSession().save(comment);
    }
    
    /**
     * <h2> dbGetCommentByPost</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return
     * @return List<Comment>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> dbGetCommentByPost(Post post) {
        StringBuffer queryBuffer = new StringBuffer(SELECT_COMMENT_HQL);
        queryBuffer.append("WHERE c.post =:post ");
        Query query = this.session.getCurrentSession().createQuery(queryBuffer.toString());
        query.setParameter("post", post);
        return query.getResultList();
    }
}
