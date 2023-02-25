package medium.vue.api.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import medium.vue.api.bl.service.UserService;
import medium.vue.api.bl.service.dto.LoginResponseDTO;
import medium.vue.api.bl.service.dto.LogoutDTO;
import medium.vue.api.bl.service.dto.UserResponseDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.common.Constants;
import medium.vue.api.common.Constants.Character;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.LoginForm;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> LoginController Class</h2>
 * <p>
 * Process for Displaying LoginController
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class LoginController {
    /**
     * <h2> userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;
    
    /**
     * <h2>servletContext</h2>
     * <p>
     * servletContext
     * </p>
     */
    @Autowired
    private ServletContext servletContext;

    /**
     * <h2> commonUtil</h2>
     * <p>
     * commonUtil
     * </p>
     */
    @Autowired
    private CommonUtil commonUtil;
    
    /**
     * <h2> tokenConfig</h2>
     * <p>
     * tokenConfig
     * </p>
     */
    @Autowired
    private TokenConfig tokenConfig;

//    @Autowired
//    private MessageSource messageSource;

    /**
     * <h2> registerUser</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity<?> registerUser() {
        UserForm userForm = new UserForm();
        UserResponseDTO response = new UserResponseDTO();
        response.setUserForm(userForm);
        response.setTimeStamp(new Date());
        response.setResponseCode(200);
        response.setResponse_description(HttpStatus.OK.toString());
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }

    /**
     * <h2> registerUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @param result
     * @param request
     * @return
     * @return ResponseEntity<?>
     * @throws IOException 
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserForm userForm, BindingResult result,
            HttpServletRequest request) throws IOException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.ACCEPTED);
        }
        UserResponseDTO response = new UserResponseDTO();
        if (userForm.getFile() != null && userForm.getFile().getSize() > 0
                && !Constants.IMAGE_ATTACH_FILE_TYPES.contains(userForm.getFile().getContentType())) {
            response.setResponse_description("Wrong File Type");
            response.setResponseCode(101);
            return new ResponseEntity<UserResponseDTO>(response, HttpStatus.ACCEPTED);
        }
        if (userForm.getFile() != null && userForm.getFile().getSize() > 2000000) {
            response.setResponse_description("File size is more than 2Mb");
            response.setResponseCode(101);
            return new ResponseEntity<UserResponseDTO>(response, HttpStatus.ACCEPTED);
        }
        if (this.validateInput(userForm) != null) {
            response.setResponseCode(HttpStatus.ACCEPTED.value());
            response.setResponse_description("Validation Failed!!");
            response.setErrors(this.validateInput(userForm));
            return new ResponseEntity<UserResponseDTO>(response, HttpStatus.ACCEPTED);
        }
        response.setUserForm(userForm);
        this.userService.doSaveUser(userForm, servletContext.getRealPath(Character.SLASH));
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponse_description("Register Successfully!!");
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }
    
    /**
     * <h2> login</h2>
     * <p>
     * 
     * </p>
     *
     * @param loginForm
     * @param result
     * @param request
     * @return
     * @throws JsonProcessingException
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.ACCEPTED);
        }
        LoginResponseDTO response = new LoginResponseDTO();
        response.setTimeStamp(new Date());
        //Employee employee = this.loginService.doGetEmployeeById(loginForm.getLoginId());
        UserForm user = this.userService.doGetUserByEmail(loginForm.getEmail());
        if (user == null) {
            response.setResponseCode(HttpStatus.ACCEPTED.value());
            response.setResponseDescription("User Does not Exist!!");
            return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.ACCEPTED);
        }
        if (!user.getPassword().equals(loginForm.getPassword())) {
            response.setResponseCode(HttpStatus.ACCEPTED.value());
            response.setResponseDescription("Password is Incorrect");
            return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.ACCEPTED);
        }
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDescription("Login Successfully");
        response.setToken(tokenConfig.generateToken(user));
        response.setUserInfo(user);
        return new ResponseEntity<LoginResponseDTO>(response, HttpStatus.OK);
    }
    
    /**
     * <h2> refreshToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @throws JsonProcessingException
     * @return ResponseEntity<?>
     */
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws JsonProcessingException {
        String token = tokenConfig.getBearerToken(request);
        String newToken = (token == null) ? null : tokenConfig.refreshToken(token);
        LogoutDTO response = new LogoutDTO();
        response.setTimeStamp(new Date());
        if (token == null || newToken == null) {
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());
            response.setResponseDescription("Token refresh was failed!!");
            return new ResponseEntity<LogoutDTO>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setToken(newToken);
            response.setResponseCode(HttpStatus.OK.value());
            response.setResponseDescription("Token refresh was successful.");
            return new ResponseEntity<LogoutDTO>(response, HttpStatus.OK);
        }
    }
    
    /**
     * <h2> validateInput</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @return
     * @return String
     */
    private String validateInput(UserForm userForm) {
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            return "Password & Confirm Password should be the same!!";
        }
        if (this.userService.doGetUserByEmail(userForm.getEmail()) != null) {
            return "Email already Exist!!";
        }
        return null;
    }
}