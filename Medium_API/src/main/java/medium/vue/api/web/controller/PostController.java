package medium.vue.api.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.bl.service.dto.LoginDTO;
import medium.vue.api.bl.service.dto.PostDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.PostForm;
import medium.vue.api.web.form.UserForm;

@RestController
@RequestMapping(value = "/api")
public class PostController {
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private TokenConfig tokenConfig;

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public ResponseEntity<?> savePost(@Valid @RequestBody PostForm postForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        LoginDTO loginUser = this.tokenConfig.getLoginEmail(request);
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        PostDTO response = new PostDTO();
        this.postService.doSavePost(postForm, loginUser.getEmail());
        response.setPostForm(postForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Register Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/postList", method = RequestMethod.GET)
    public ResponseEntity<?> postList(HttpServletRequest request, Integer limit, Integer offset) {
        List<PostForm> postFormList = this.postService.doGetPostList(null);
        PostDTO response = new PostDTO();
        response.setPostList(postFormList);
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Post List!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public ResponseEntity<?> editPost(@RequestParam Integer postId,  HttpServletRequest request, Integer limit, Integer offset) {
        PostForm postForm = this.postService.doGetPostById(postId);
        PostDTO response = new PostDTO();
        response.setPostForm(postForm);
        response.setTimeStamp(new Date());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Post Edit Form!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostForm editForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        PostDTO response = new PostDTO();
        this.postService.doUpdatePost(editForm);
        PostForm postForm = this.postService.doGetPostById(editForm.getPostId());
        response.setPostForm(postForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Update Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public ResponseEntity<?> deletePost(@RequestParam Integer postId) {
        PostDTO response = new PostDTO();
        this.postService.doDeletePost(postId);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Delete Successfully!!");
        return new ResponseEntity<PostDTO>(response, HttpStatus.OK);
    }
}