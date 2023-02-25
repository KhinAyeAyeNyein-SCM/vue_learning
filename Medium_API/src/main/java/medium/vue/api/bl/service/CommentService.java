package medium.vue.api.bl.service;

import java.util.List;

import medium.vue.api.web.form.CommentForm;

public interface CommentService {

    /**
     * <h2> doSaveComment</h2>
     * <p>
     * 
     * </p>
     *
     * @param commentForm
     * @param userEmail
     * @return void
     */
    public void doSaveComment(CommentForm commentForm, String userEmail);

    /**
     * <h2> doGetCommentByPostId</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @param realPath TODO
     * @return
     * @return List<CommentForm>
     */
    public List<CommentForm> doGetCommentByPostId(Integer postId, String realPath);

}
