package medium.vue.api.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.Comment;

/**
 * <h2> CommentForm Class</h2>
 * <p>
 * Process for Displaying CommentForm
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm implements Serializable {
    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <h2> commentId</h2>
     * <p>
     * commentId
     * </p>
     */
    private Integer commentId;

    /**
     * <h2> user</h2>
     * <p>
     * user
     * </p>
     */
    private UserForm user;

    /**
     * <h2> post</h2>
     * <p>
     * post
     * </p>
     */
    private PostForm post;
    
    /**
     * <h2> postId</h2>
     * <p>
     * postId
     * </p>
     */
    private Integer postId;

    /**
     * <h2> parentCommentId</h2>
     * <p>
     * parentCommentId
     * </p>
     */
    private Integer parentCommentId;
    
    /**
     * <h2> body</h2>
     * <p>
     * body
     * </p>
     */
    @NotEmpty
    private String body;
    
    /**
     * <h2> Constructor for CommentForm </h2>
     * <p>
     * Constructor for CommentForm
     * </p>
     * @param comment
     */
    public CommentForm (Comment comment) {
        this.commentId = comment.getId();
        this.body = comment.getBody();
        this.parentCommentId = comment.getParentCommentId();
    }
}
