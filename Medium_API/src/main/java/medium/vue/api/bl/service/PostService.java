package medium.vue.api.bl.service;

import java.io.IOException;
import java.util.List;

import medium.vue.api.web.form.PostForm;

/**
 * <h2> PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface PostService {
    /**
     * <h2> doSavePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param userEmail
     * @param filePath TODO
     * @return void
     * @throws IOException 
     */
    public void doSavePost(PostForm postForm, String userEmail, String filePath) throws IOException;

    /**
     * <h2> doGetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchForm
     * @param realPath TODO
     * @return
     * @return List<PostForm>
     */
    public List<PostForm> doGetPostList(PostForm searchForm, String realPath);

    /**
     * <h2> doGetPostListByUserId</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @param realPath
     * @return
     * @return List<PostForm>
     */
    public List<PostForm> doGetPostListByUserId(Integer userId, String realPath);

    /**
     * <h2> doGetPostById</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @param realPath TODO
     * @return
     * @return PostForm
     */
    public PostForm doGetPostById(Integer postId, String realPath);

    /**
     * <h2> doGetPostCountByUserId</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return Integer
     */
    public Integer doGetPostCountByUserId(Integer userId);

    /**
     * <h2> doUpdatePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param updatedForm
     * @return void
     */
    public void doUpdatePost(PostForm updatedForm);

    /**
     * <h2> doDeletePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return void
     */
    public void doDeletePost(Integer postId);
}