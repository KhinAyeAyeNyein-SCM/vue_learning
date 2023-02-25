package medium.vue.api.web.form;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.Comment;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;

/**
 * <h2> PostForm Class</h2>
 * <p>
 * Process for Displaying PostForm
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class PostForm implements Serializable{
    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * <h2> postId</h2>
     * <p>
     * postId
     * </p>
     */
    private Integer postId;
    
    /**
     * <h2> user</h2>
     * <p>
     * user
     * </p>
     */
    private User user;
    
    /**
     * <h2> title</h2>
     * <p>
     * title
     * </p>
     */
    @NotEmpty
    private String title;
    
    /**
     * <h2> description</h2>
     * <p>
     * description
     * </p>
     */
    @NotEmpty
    private String description;
    
    /**
     * <h2> image</h2>
     * <p>
     * image
     * </p>
     */
    private String image;
    
    /**
     * <h2> file</h2>
     * <p>
     * file
     * </p>
     */
    private MultipartFile file;
    
    /**
     * <h2> categoryList</h2>
     * <p>
     * categoryList
     * </p>
     */
    private List<CategoryForm> categoryList;
    
    /**
     * <h2> categories</h2>
     * <p>
     * categories
     * </p>
     */
    private String categories;
    
    /**
     * <h2> comment</h2>
     * <p>
     * comment
     * </p>
     */
    private List<CommentForm> comment;
    
    /**
     * <h2> Constructor for PostForm </h2>
     * <p>
     * Constructor for PostForm
     * </p>
     * @param post
     */
    public PostForm(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.image = post.getImage();
    }
}
