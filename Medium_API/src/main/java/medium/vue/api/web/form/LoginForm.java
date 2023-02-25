package medium.vue.api.web.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String password;
}
