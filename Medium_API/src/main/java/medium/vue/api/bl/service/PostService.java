package medium.vue.api.bl.service;

import java.util.List;

import medium.vue.api.web.form.PostForm;

public interface PostService {

    /**
     * <h2> doSavePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param userEmail
     * @return void
     */
    public void doSavePost(PostForm postForm, String userEmail);

    /**
     * <h2> doGetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchForm
     * @return
     * @return List<PostForm>
     */
    public List<PostForm> doGetPostList(PostForm searchForm);

    /**
     * <h2> doGetPostById</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return
     * @return PostForm
     */
    public PostForm doGetPostById(Integer postId);

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

    public void doDeletePost(Integer postId);
}