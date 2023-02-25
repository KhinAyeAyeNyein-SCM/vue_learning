package medium.vue.api.persistence.dao;

import java.util.List;

import medium.vue.api.persistence.entity.Comment;
import medium.vue.api.persistence.entity.Post;

public interface CommentDAO {

    /**
     * <h2> dbSaveComment</h2>
     * <p>
     * 
     * </p>
     *
     * @param comment
     * @return void
     */
    public void dbSaveComment(Comment comment);

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
    public List<Comment> dbGetCommentByPost(Post post);

}
