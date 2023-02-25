package medium.vue.api.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int postId;
    
    private User user;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String description;
    
    private String image;
    
    private MultipartFile file;
    
    public PostForm(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.image = post.getImage();
    }
}
