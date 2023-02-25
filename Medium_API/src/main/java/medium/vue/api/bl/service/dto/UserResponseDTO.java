package medium.vue.api.bl.service.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.web.form.CategoryForm;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> UserResponseDTO Class</h2>
 * <p>
 * Process for Displaying UserResponseDTO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    /**
     * <h2> timeStamp</h2>
     * <p>
     * timeStamp
     * </p>
     */
    private Date timeStamp;

    /**
     * <h2> responseCode</h2>
     * <p>
     * responseCode
     * </p>
     */
    private Integer responseCode;

    /**
     * <h2> response_description</h2>
     * <p>
     * response_description
     * </p>
     */
    private String response_description;

    /**
     * <h2> userList</h2>
     * <p>
     * userList
     * </p>
     */
    private List<UserForm> userList;

    /**
     * <h2> userForm</h2>
     * <p>
     * userForm
     * </p>
     */
    private UserForm userForm;
    
    /**
     * <h2> postList</h2>
     * <p>
     * postList
     * </p>
     */
    private List<PostForm> postList;
    
    /**
     * <h2> email</h2>
     * <p>
     * email
     * </p>
     */
    private String email;
    
    /**
     * <h2> name</h2>
     * <p>
     * name
     * </p>
     */
    private String name;
    
    /**
     * <h2> errors</h2>
     * <p>
     * errors
     * </p>
     */
    private String errors;

    /**
     * <h2> categories</h2>
     * <p>
     * categories
     * </p>
     */
    private List<CategoryForm> categories;
    
    /**
     * <h2> Constructor for UserResponseDTO </h2>
     * <p>
     * Constructor for UserResponseDTO
     * </p>
     * @param user
     */
    public UserResponseDTO(UserForm user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
