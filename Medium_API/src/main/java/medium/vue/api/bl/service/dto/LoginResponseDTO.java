package medium.vue.api.bl.service.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.web.form.UserForm;

/**
 * <h2> LoginResponseDTO Class</h2>
 * <p>
 * Process for Displaying LoginResponseDTO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO implements Serializable{
    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <h2> timeStamp</h2>
     * <p>
     * timeStamp
     * </p>
     */
    private Date timeStamp;
    
    /**
     * <h2> responseCode</h2>
     * <p>
     * responseCode
     * </p>
     */
    private Integer responseCode;
    
    /**
     * <h2> responseDescription</h2>
     * <p>
     * responseDescription
     * </p>
     */
    private String responseDescription;
    
    /**
     * <h2> token</h2>
     * <p>
     * token
     * </p>
     */
    private String token;
    
    /**
     * <h2> userInfo</h2>
     * <p>
     * userInfo
     * </p>
     */
    private UserForm userInfo;
    
    /**
     * <h2> Constructor for LoginResponseDTO </h2>
     * <p>
     * Constructor for LoginResponseDTO
     * </p>
     * @param responseCode
     * @param responseDescription
     */
    public LoginResponseDTO(Integer responseCode, String responseDescription) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
    }
}