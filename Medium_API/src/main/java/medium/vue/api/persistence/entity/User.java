package medium.vue.api.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> User Class</h2>
 * <p>
 * Process for Displaying User
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * <h2> id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * <h2> name</h2>
     * <p>
     * name
     * </p>
     */
    @Column(name = "name")
    private String name;
    
    /**
     * <h2> email</h2>
     * <p>
     * email
     * </p>
     */
    @Column(name = "email")
    private String email;
    
    /**
     * <h2> bio</h2>
     * <p>
     * bio
     * </p>
     */
    @Column(name = "bio")
    private String bio;
    
    /**
     * <h2> password</h2>
     * <p>
     * password
     * </p>
     */
    @Column(name = "password")
    private String password;
    
    /**
     * <h2> profile</h2>
     * <p>
     * profile
     * </p>
     */
    @Column(name = "profile") 
    private String profile;
    
    /**
     * <h2> createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name ="created_at")
    private Date createdAt;
    
    /**
     * <h2> updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;
    
    /**
     * <h2> posts</h2>
     * <p>
     * posts
     * </p>
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Post> posts;
    
    /**
     * <h2> comment</h2>
     * <p>
     * comment
     * </p>
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comment;
    
    /**
     * <h2> Constructor for User </h2>
     * <p>
     * Constructor for User
     * </p>
     * @param userForm
     */
    public User(UserForm userForm) {
        this.id = userForm.getId();
        this.name = userForm.getName();
        this.email = userForm.getEmail();
        this.bio = userForm.getBio();
        this.password = userForm.getPassword();
        this.profile = userForm.getProfile();
    }
}