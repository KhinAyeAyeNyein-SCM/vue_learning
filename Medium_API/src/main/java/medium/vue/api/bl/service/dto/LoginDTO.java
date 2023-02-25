package medium.vue.api.bl.service.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer loginId;
    
    private String name;
    
    private String email;
    
    private String password;
    
    public LoginDTO(User user) {
        this.loginId = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }
}
