package medium.vue.api.bl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medium.vue.api.bl.service.CommentService;
import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.persistence.dao.CommentDAO;
import medium.vue.api.persistence.dao.PostDAO;
import medium.vue.api.persistence.entity.Comment;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.CommentForm;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> CommentServiceImpl Class</h2>
 * <p>
 * Process for Displaying CommentServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    /**
     * <h2> commentDAO</h2>
     * <p>
     * commentDAO
     * </p>
     */
    @Autowired
    private CommentDAO commentDAO;

    /**
     * <h2> postDAO</h2>
     * <p>
     * postDAO
     * </p>
     */
    @Autowired
    private PostDAO postDAO;
    
    /**
     * <h2> postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;
    
    /**
     * <h2> userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;
    
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
    @Override
    public void doSaveComment(CommentForm commentForm, String userEmail) {
        Comment comment = new Comment(commentForm);
        UserForm userForm = this.userService.doGetUserByEmail(userEmail);
        User user = new User(userForm);
        Post post = this.postDAO.dbGetPostById(commentForm.getPostId());
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(new Date());
        this.commentDAO.dbSaveComment(comment);
    }
    
    /**
     * <h2> doGetCommentByPostId</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return
     * @return List<CommentForm>
     */
    @Override
    public List<CommentForm> doGetCommentByPostId(Integer postId, String realPath) {
        Post post = this.postDAO.dbGetPostById(postId);
        List<Comment> commentList = this.commentDAO.dbGetCommentByPost(post);
        List<CommentForm> commentFormList = new ArrayList<CommentForm>();
        UserForm user;
        for(Comment comment : commentList) {
            PostForm postForm = new PostForm(post);
            CommentForm commentForm = new CommentForm(comment);
            commentForm.setPost(postForm);
            user = this.userService.doGetUserById(comment.getUser().getId(), realPath);
            commentForm.setUser(user);
            commentFormList.add(commentForm);
        }
        return commentFormList;
    }
}
