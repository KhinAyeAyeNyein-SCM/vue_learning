package medium.vue.api.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import medium.vue.api.bl.service.CategoryService;
import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.bl.service.dto.UserResponseDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.CategoryForm;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class UserController {
    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2> userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2> commonUtil</h2>
     * <p>
     * commonUtil
     * </p>
     */
    @Autowired
    private CommonUtil commonUtil;
    
    /**
     * <h2> categoryService</h2>
     * <p>
     * categoryService
     * </p>
     */
    @Autowired
    private CategoryService categoryService;
    
    /**
     * <h2> postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

    /**
     * <h2> profileDetail</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @param request
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> profileDetail(@PathVariable Integer id, HttpServletRequest request) {
        String path = session.getServletContext().getRealPath("/");
        UserForm user = this.userService.doGetUserById(id, path);
        UserResponseDTO response = new UserResponseDTO();
        List<CategoryForm> categories = this.categoryService.doGetCategoryList();
        List<PostForm> postFormList = this.postService.doGetPostListByUserId(id, path);
        response.setCategories(categories);
        response.setPostList(postFormList);
        response.setUserForm(user);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponse_description("");
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2> updateUser</h2>
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
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserForm editForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        UserResponseDTO response = new UserResponseDTO();
        this.userService.doUpdateUser(editForm);
        String path = session.getServletContext().getRealPath("/");
        UserForm userForm = this.userService.doGetUserById(editForm.getId(), path);
        response.setUserForm(userForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponse_description("Update Successfully!!");
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }
}