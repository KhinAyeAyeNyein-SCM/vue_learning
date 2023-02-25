package medium.vue.api.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import medium.vue.api.bl.service.CategoryService;
import medium.vue.api.bl.service.CommentService;
import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.dto.LoginDTO;
import medium.vue.api.bl.service.dto.PostDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.common.Constants;
import medium.vue.api.common.Constants.Character;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.CategoryForm;
import medium.vue.api.web.form.PostForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class PostController {
    /**
     * <h2> session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;
    /**
     * <h2>servletContext</h2>
     * <p>
     * servletContext
     * </p>
     */
    @Autowired
    private ServletContext servletContext;
    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

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
     * <h2>commonUtil</h2>
     * <p>
     * commonUtil
     * </p>
     */
    @Autowired
    private CommonUtil commonUtil;

    /**
     * <h2>tokenConfig</h2>
     * <p>
     * tokenConfig
     * </p>
     */
    @Autowired
    private TokenConfig tokenConfig;

    /**
     * <h2>savePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/savePost", method = RequestMethod.GET)
    public ResponseEntity<?> savePost(HttpServletRequest request) {
        PostForm postForm = new PostForm();
        PostDTO response = new PostDTO();
        List<CategoryForm> categories = this.categoryService.doGetCategoryList();
        postForm.setCategoryList(categories);
        response.setPostForm(postForm);
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Post Register Form!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2>savePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param result
     * @param request
     * @return
     * @return ResponseEntity<?>
     * @throws IOException
     */
    @RequestMapping(value = "/savePost", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> savePost(@Valid @ModelAttribute PostForm postForm, BindingResult result,
            HttpServletRequest request) throws IOException {
        LoginDTO loginUser = this.tokenConfig.getLoginEmail(request);
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        PostDTO response = new PostDTO();
        if (postForm.getFile() != null && postForm.getFile().getSize() > 0
                && !Constants.IMAGE_ATTACH_FILE_TYPES.contains(postForm.getFile().getContentType())) {
            response.setResponseDescription("Wrong file type");
            response.setResponseCode(101);
            return new ResponseEntity<PostDTO>(response, HttpStatus.ACCEPTED);
        }
        if (postForm.getFile() != null && postForm.getFile().getSize() > 2000000) {
            response.setResponseDescription("File size is more than 2Mb");
            response.setResponseCode(101);
            return new ResponseEntity<PostDTO>(response, HttpStatus.ACCEPTED);
        }
        postForm.setFile(postForm.getFile());
        this.postService.doSavePost(postForm, loginUser.getEmail(), servletContext.getRealPath(Character.SLASH));
        response.setPostForm(postForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Register Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2>postList</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @param limit
     * @param offset
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/postList", method = RequestMethod.GET)
    public ResponseEntity<?> postList(HttpServletRequest request, Integer limit, Integer offset) {
        String path = session.getServletContext().getRealPath("/");
        List<PostForm> postFormList = this.postService.doGetPostList(null, path);
        PostDTO response = new PostDTO();
        List<CategoryForm> categories = this.categoryService.doGetCategoryList();
        response.setCategories(categories);
        response.setPostList(postFormList);
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Post List!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2>editPost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @param request
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/editPost/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> editPost(@PathVariable Integer postId, HttpServletRequest request) {
        String path = session.getServletContext().getRealPath("/");
        PostForm postForm = this.postService.doGetPostById(postId, path);
        PostDTO response = new PostDTO();
        response.setPostForm(postForm);
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Post Edit Form!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param editForm
     * @param result
     * @param request
     * @return
     * @throws JsonProcessingException
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostForm editForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        PostDTO response = new PostDTO();
        this.postService.doUpdatePost(editForm);
        String path = session.getServletContext().getRealPath("/");
        PostForm postForm = this.postService.doGetPostById(editForm.getPostId(), path);
        response.setPostForm(postForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Update Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * 
     * </p>
     *
     * @param postId
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public ResponseEntity<?> deletePost(@RequestParam Integer postId) {
        PostDTO response = new PostDTO();
        this.postService.doDeletePost(postId);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Delete Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }
}