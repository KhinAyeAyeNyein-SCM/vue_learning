package medium.vue.api.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.web.form.PostForm;

/**
 * <h2> Posts Class</h2>
 * <p>
 * Process for Displaying Posts
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post implements Serializable{
    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * <h2> id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * <h2> user</h2>
     * <p>
     * user
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    /**
     * <h2> title</h2>
     * <p>
     * title
     * </p>
     */
    @Column(name = "title")
    private String title;
    
    /**
     * <h2> description</h2>
     * <p>
     * description
     * </p>
     */
    @Column(name = "description")
    private String description;
    
    /**
     * <h2> image</h2>
     * <p>
     * image
     * </p>
     */
    @Column(name = "image")
    private String image;
    
    /**
     * <h2> createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
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
     * <h2> deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;
    
    /**
     * <h2> comment</h2>
     * <p>
     * comment
     * </p>
     */
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comment;
    
    /**
     * <h2> category</h2>
     * <p>
     * category
     * </p>
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "post_categories", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> category = new ArrayList<Category>();
    
    /**
     * <h2> Constructor for Posts </h2>
     * <p>
     * Constructor for Posts
     * </p>
     * @param postForm
     */
    public Post(PostForm postForm) {
        this.id = postForm.getPostId();
        this.user = postForm.getUser();
        this.title = postForm.getTitle();
        this.description = postForm.getDescription();
        this.image = postForm.getImage();
    }
}
