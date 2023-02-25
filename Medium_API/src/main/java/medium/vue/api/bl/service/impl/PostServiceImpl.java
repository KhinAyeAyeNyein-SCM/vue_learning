package medium.vue.api.bl.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import medium.vue.api.bl.service.CategoryService;
import medium.vue.api.bl.service.CommentService;
import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.common.Constants.Character;
import medium.vue.api.common.Constants.Storage;
import medium.vue.api.persistence.dao.PostDAO;
import medium.vue.api.persistence.dao.UserDAO;
import medium.vue.api.persistence.entity.Category;
import medium.vue.api.persistence.entity.Post;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.CategoryForm;
import medium.vue.api.web.form.CommentForm;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {
    /**
     * <h2>postDAO</h2>
     * <p>
     * postDAO
     * </p>
     */
    @Autowired
    private PostDAO postDAO;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;
    
    /**
     * <h2> userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <h2>categoryService</h2>
     * <p>
     * categoryService
     * </p>
     */
    @Autowired
    private CategoryService categoryService;
    
    /**
     * <h2> commentService</h2>
     * <p>
     * commentService
     * </p>
     */
    @Autowired
    private CommentService commentService;

    /**
     * <h2>doSavePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param userEmail
     * @return void
     * @throws IOException
     */
    @Override
    public void doSavePost(PostForm postForm, String userEmail, String filePath) throws IOException {
        if (postForm.getFile() != null && postForm.getFile().getSize() > 0) {
            String fileName = this.writeLeaveAttachFile(postForm.getFile(), postForm.getTitle(), filePath);
            postForm.setImage(fileName);
        }
        Post post = new Post(postForm);
        String[] categoryArr = postForm.getCategories().split(",");
        Category category;
        List<Category> categoryList = new ArrayList<Category>();
        for (int i = 0; i < categoryArr.length; i++) {
            category = this.categoryService.doGetCategoryByName(categoryArr[i]);
            categoryList.add(category);
        }
        post.setCategory(categoryList);
        post.setCreatedAt(new Date());
        UserForm userForm = this.userService.doGetUserByEmail(userEmail);
        User user = new User(userForm);
        post.setUser(user);
        this.postDAO.dbSavePost(post);
    }

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchForm
     * @return
     * @return List<PostForm>
     */
    @Override
    public List<PostForm> doGetPostList(PostForm searchForm, String realPath) {
        List<Post> postList = this.postDAO.dbGetPostList(searchForm);
        PostForm postForm;
        List<PostForm> postFormList = new ArrayList<PostForm>();
        for (Post post : postList) {
            postForm = new PostForm(post);
            String path = Storage.IMG_PATH + post.getTitle() + Character.SLASH + post.getImage();
            if (post.getImage() != null && !post.getImage().isEmpty()) {
                boolean isExist = this.isExistPhoto(path, realPath);
                if (isExist) {
                    postForm.setImage(path);
                } else
                    postForm.setImage(Storage.IMG_PATH + "noImage.jpg");
            } else
                postForm.setImage(Storage.IMG_PATH + "noImage.jpg");
            Category categories;
            UserForm user;
            List<CategoryForm> categoryList = new ArrayList<CategoryForm>();
            user = this.userService.doGetUserById(post.getUser().getId(), realPath);
            postForm.setUser(new User(user));
            for (Category category : post.getCategory()) {
                categories = this.categoryService.doGetCategoryByName(category.getName());
                categoryList.add(new CategoryForm(categories));
            }
            postForm.setCategoryList(categoryList);
            postFormList.add(postForm);
        }
        return postFormList;
    }
    
    /**
     * <h2> doGetPostListByUserId</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @param realPath
     * @return
     * @return List<PostForm>
     */
    @Override
    public List<PostForm> doGetPostListByUserId(Integer userId, String realPath) {
        User user = this.userDAO.dbGetUserById(userId);
        List<Post> postList = this.postDAO.dbGetPosListByUserId(user);
        PostForm postForm;
        List<PostForm> postFormList = new ArrayList<PostForm>();
        for (Post post : postList) {
            postForm = new PostForm(post);
            String path = Storage.IMG_PATH + post.getTitle() + Character.SLASH + post.getImage();
            if (post.getImage() != null && !post.getImage().isEmpty()) {
                boolean isExist = this.isExistPhoto(path, realPath);
                if (isExist) {
                    postForm.setImage(path);
                } else
                    postForm.setImage(Storage.IMG_PATH + "noImage.jpg");
            } else
                postForm.setImage(Storage.IMG_PATH + "noImage.jpg");
            Category categories;
            UserForm userForm;
            List<CategoryForm> categoryList = new ArrayList<CategoryForm>();
            userForm = this.userService.doGetUserById(post.getUser().getId(), null);
            postForm.setUser(new User(userForm));
            for (Category category : post.getCategory()) {
                categories = this.categoryService.doGetCategoryByName(category.getName());
                categoryList.add(new CategoryForm(categories));
            }
            postForm.setCategoryList(categoryList);
            postFormList.add(postForm);
        }
        return postFormList;
    }

    /**
     * <h2>doGetPostById</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return
     * @return PostForm
     */
    @Override
    public PostForm doGetPostById(Integer postId, String realPath) {
        Post post = this.postDAO.dbGetPostById(postId);
        if (post == null) {
            return null;
        }
        PostForm postform = new PostForm(post);
        String path = Storage.IMG_PATH + post.getTitle() + Character.SLASH + post.getImage();
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            boolean isExist = this.isExistPhoto(path, realPath);
            if (isExist) {
                postform.setImage(path);
            } else
                postform.setImage(Storage.IMG_PATH + "noImage.jpg");
        } else
            postform.setImage(Storage.IMG_PATH + "noImage.jpg");
        UserForm userForm = this.userService.doGetUserById(post.getUser().getId(), realPath);
        postform.setUser(new User(userForm));
        Category categories;
        List<CategoryForm> categoryList = new ArrayList<CategoryForm>();
        for (Category category : post.getCategory()) {
            categories = this.categoryService.doGetCategoryByName(category.getName());
            categoryList.add(new CategoryForm(categories));
        }
        List<CommentForm> commentList = this.commentService.doGetCommentByPostId(postId, realPath);
        postform.setComment(commentList);
        postform.setCategoryList(categoryList);
        return postform;
    }
    
    /**
     * <h2> doGetPostCountByUserId</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return Integer
     */
    @Override
    public Integer doGetPostCountByUserId(Integer userId) {
        User user = this.userDAO.dbGetUserById(userId);
        return this.postDAO.dbGetPosListByUserId(user).size();
    }

    /**
     * <h2>doUpdatePost</h2>
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

    /**
     * <h2> doDeletePost </h2>
     * <p>
     * 
     * </p>
     * 
     * @param postId
     */
    @Override
    public void doDeletePost(Integer postId) {
        Post post = this.postDAO.dbGetPostById(postId);
        post.setDeletedAt(new Date());
        this.postDAO.dbUpdatePost(post);
    }

    /**
     * <h2>writeLeaveAttachFile</h2>
     * <p>
     * 
     * </p>
     *
     * @param attachFile
     * @param employeeId
     * @param fileUploadPath
     * @return
     * @throws IOException
     * @return String
     */
    private String writeLeaveAttachFile(MultipartFile attachFile, String postTitle, String fileUploadPath)
            throws IOException {
        String leaveAttachDirPath = fileUploadPath + Storage.IMG_PATH + postTitle;
        byte[] bytes = attachFile.getBytes();
        File attachDir = new File(leaveAttachDirPath);
        if (!attachDir.exists()) {
            attachDir.mkdirs();
        }
        String fileName = postTitle + System.currentTimeMillis() + "."
                + FilenameUtils.getExtension(attachFile.getOriginalFilename());
        File serverFile = new File(attachDir.getAbsolutePath() + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return fileName;
    }

    /**
     * <h2> isExistPhoto</h2>
     * <p>
     * 
     * </p>
     *
     * @param photoPath
     * @param realContextPath
     * @return
     * @return boolean
     */
    private boolean isExistPhoto(String photoPath, String realContextPath) {
        File photo = new File(realContextPath + photoPath);
        if (!photo.exists()) {
            return false;
        }
        return true;
    }
}
