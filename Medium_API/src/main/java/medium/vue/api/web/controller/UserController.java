package medium.vue.api.web.controller;

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

import medium.vue.api.bl.service.UserService;
import medium.vue.api.bl.service.dto.UserResponseDTO;
import medium.vue.api.bl.service.dto.ValidationErrorDTO;
import medium.vue.api.common.CommonUtil;
import medium.vue.api.config.TokenConfig;
import medium.vue.api.web.form.UserForm;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private TokenConfig tokenConfig;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<?> profileDetail(@RequestParam Integer id, HttpServletRequest request) {
        UserForm user = this.userService.doGetUserById(id);
        UserResponseDTO response = new UserResponseDTO();
        response.setUserForm(user);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponse_description("");
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserForm editForm, BindingResult result,
            HttpServletRequest request) throws JsonProcessingException {
        if (result.hasErrors()) {
            return new ResponseEntity<ValidationErrorDTO>(commonUtil.getValidationFailResponse(result),
                    HttpStatus.BAD_REQUEST);
        }
        UserResponseDTO response = new UserResponseDTO();
        this.userService.doUpdateUser(editForm);
        UserForm userForm = this.userService.doGetUserById(editForm.getId());
        response.setUserForm(userForm);
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponse_description("Update Successfully!!");
        return new ResponseEntity<UserResponseDTO>(response, HttpStatus.OK);
    }
}