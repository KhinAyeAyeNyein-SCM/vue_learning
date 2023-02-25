package medium.vue.api.bl.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.CategoryForm;
import medium.vue.api.web.form.PostForm;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO implements Serializable{
private static final long serialVersionUID = 1L;
    
    private Integer postId;
    
    private User user;
    
    private String title;
    
    private String description;
    
    private String image;

    private Map<String, String> errors;
    
    private Date timeStamp;
    
    private String responseDescription;
    
    private Integer responseCode;
    
    private List<PostForm> postList;
    
    private List<CategoryForm> categories;
    
    private PostForm postForm;
}
