package medium.vue.api.bl.service.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * <h2> LogoutDTO Class</h2>
 * <p>
 * Process for Displaying LogoutDTO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogoutDTO implements Serializable{
    /**
     * <h2> serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;
    /**
     * <h2>timeStamp</h2>
     * <p>
     * timeStamp
     * </p>
     */
    private Date timeStamp;

    /**
     * <h2>responseCode</h2>
     * <p>
     * responseCode
     * </p>
     */
    private Integer responseCode;

    /**
     * <h2>responseDescription</h2>
     * <p>
     * responseDescription
     * </p>
     */
    private String responseDescription;

    /**
     * <h2>token</h2>
     * <p>
     * token
     * </p>
     */
    private String token;
}