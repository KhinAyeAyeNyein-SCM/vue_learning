package medium.vue.api.bl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.persistence.dao.PostDAO;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Service
@Transactional
public class PostServiceImpl implements PostService{
    /**
     * <h2> postDAO</h2>
     * <p>
     * postDAO
     * </p>
     */
    @Autowired
    private PostDAO postDAO;
    
    /**
     * <h2> userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;
    
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
    @Override
    public void doSavePost(PostForm postForm, String userEmail) {
        Post post = new Post(postForm);
        post.setCreatedAt(new Date());
        UserForm userForm = this.userService.doGetUserByEmail(userEmail);
        User user = new User(userForm);
        post.setUser(user);
        this.postDAO.dbSavePost(post);
    }
    
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
    @Override
    public List<PostForm> doGetPostList(PostForm searchForm) {
        List<Post> postList = this.postDAO.dbGetPostList(searchForm);
        PostForm postForm;
        List<PostForm> postFormList = new ArrayList<PostForm>();
        for (Post post : postList) {
            postForm = new PostForm(post);
            postFormList.add(postForm);
        }
        return postFormList;
    }
    
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
    @Override
    public PostForm doGetPostById(Integer postId) {
        Post post = this.postDAO.dbGetPostById(postId);
        if (post == null) {
            return null;
        }
        PostForm postform = new PostForm(post);
        UserForm userForm = this.userService.doGetUserById(post.getUser().getId());
        postform.setUser(new User(userForm));
        return postform;
    }
    
    /**
     * <h2> doUpdatePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param updatedForm
     * @return void
     */
    @Override
    public void doUpdatePost(PostForm updatedForm) {
        Post post = this.postDAO.dbGetPostById(updatedForm.getPostId());
        post.setTitle(updatedForm.getTitle());
        post.setDescription(updatedForm.getDescription());
        post.setUpdatedAt(new Date());
        this.postDAO.dbUpdatePost(post);
    }
    
    @Override
    public void doDeletePost(Integer postId) {
        Post post = this.postDAO.dbGetPostById(postId);
        post.setDeletedAt(new Date());
        this.postDAO.dbUpdatePost(post);
    }
}
