package medium.vue.api.bl.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medium.vue.api.web.form.CommentForm;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer commentId;
    
    private String Body;
    
    private Date timeStamp;
    
    private String responseDescription;
    
    private Integer responseCode;
    
    private List<CommentForm> commentForm;
}
