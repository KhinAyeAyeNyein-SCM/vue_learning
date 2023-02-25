package medium.vue.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import medium.vue.api.bl.service.CommentService;
import medium.vue.api.bl.service.dto.CommentDTO;
import medium.vue.api.bl.service.dto.LoginDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.CommentForm;

/**
 * <h2> CommentController Class</h2>
 * <p>
 * Process for Displaying CommentController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class CommentController {
    /**
     * <h2> commentService</h2>
     * <p>
     * commentService
     * </p>
     */
    @Autowired
    private CommentService commentService;

    /**
     * <h2> tokenConfig</h2>
     * <p>
     * tokenConfig
     * </p>
     */
    @Autowired
    private TokenConfig tokenConfig;

    /**
     * <h2> commonUtil</h2>
     * <p>
     * commonUtil
     * </p>
     */
    @Autowired
    private CommonUtil commonUtil;

    /**
     * <h2> session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;
    
    /**
     * <h2> saveComment</h2>
     * <p>
     * 
     * </p>
     *
     * @param commentForm
     * @param result
     * @param request
     * @return
     * @throws JsonProcessingException
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentForm commentForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        LoginDTO loginUser = this.tokenConfig.getLoginEmail(request);
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        CommentDTO response = new CommentDTO();
        this.commentService.doSaveComment(commentForm, loginUser.getEmail());
        String path = session.getServletContext().getRealPath("/");
        response.setCommentForm(this.commentService.doGetCommentByPostId(commentForm.getPostId(), path));
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Commented!");
        return new ResponseEntity<CommentDTO>(response, HttpStatus.OK);
    }
}