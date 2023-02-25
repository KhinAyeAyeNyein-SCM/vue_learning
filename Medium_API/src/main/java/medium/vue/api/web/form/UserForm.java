package medium.vue.api.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.User;

/**
 * <h2> UserForm Class</h2>
 * <p>
 * Process for Displaying UserForm
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    /**
     * <h2> id</h2>
     * <p>
     * id
     * </p>
     */
    private Integer id;
    
    /**
     * <h2> name</h2>
     * <p>
     * name
     * </p>
     */
    @NotEmpty
    private String name;

    /**
     * <h2> email</h2>
     * <p>
     * email
     * </p>
     */
    @NotEmpty
    @Email
    private String email;
    
    /**
     * <h2> bio</h2>
     * <p>
     * bio
     * </p>
     */
    private String bio;

    /**
     * <h2> password</h2>
     * <p>
     * password
     * </p>
     */
    @NotEmpty
    private String password;

    /**
     * <h2> confirmPassword</h2>
     * <p>
     * confirmPassword
     * </p>
     */
    @NotEmpty
    private String confirmPassword;
    
    /**
     * <h2> profile</h2>
     * <p>
     * profile
     * </p>
     */
    private String profile;
    
    /**
     * <h2> postCount</h2>
     * <p>
     * postCount
     * </p>
     */
    private Integer postCount;
    
    /**
     * <h2> file</h2>
     * <p>
     * file
     * </p>
     */
    private MultipartFile file;
    
    /**
     * <h2> Constructor for UserForm </h2>
     * <p>
     * Constructor for UserForm
     * </p>
     * @param user
     */
    public UserForm(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.password = user.getPassword();
        this.profile = user.getProfile();
    }
}
