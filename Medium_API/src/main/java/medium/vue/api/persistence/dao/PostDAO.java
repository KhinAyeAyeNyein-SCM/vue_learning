package medium.vue.api.persistence.dao;

import java.util.List;

import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.PostForm;

/**
 * <h2> PostDAO Class</h2>
 * <p>
 * Process for Displaying PostDAO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface PostDAO {
    /**
     * <h2> dbSavePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return void
     */
    public void dbSavePost(Post post);

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
    public List<Post> dbGetPostList(PostForm post);

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
    public Post dbGetPostById(Integer postId);
    
    /**
     * <h2> dbGetPostCountByUserId</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return
     * @return Integer
     */
    public List<Post> dbGetPosListByUserId(User user);

    /**
     * <h2> dbUpdatePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param post
     * @return void
     */
    public void dbUpdatePost(Post post);
}