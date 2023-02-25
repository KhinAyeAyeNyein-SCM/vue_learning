package medium.vue.api.bl.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2> ValidationErrorDTO Class</h2>
 * <p>
 * Process for Displaying ValidationErrorDTO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO implements Serializable{
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
     * <h2> errors</h2>
     * <p>
     * errors
     * </p>
     */
    private Map<String, String> errors;
   
}
