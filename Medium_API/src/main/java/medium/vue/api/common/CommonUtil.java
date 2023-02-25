package medium.vue.api.common;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Splitter;

import medium.vue.api.bl.service.dto.ValidationErrorDTO;

@Component
public class CommonUtil {
    /**
     * <h2>getValidationFailResponse</h2>
     * <p>
     * Get validation fail response
     * </p>
     * 
     * @param br
     * @return ValidationErrorDTO
     */
    public ValidationErrorDTO getValidationFailResponse(BindingResult br)
            throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        for (FieldError e : br.getFieldErrors()) {
            sb.append(e.getField()).append(":").append(e.getDefaultMessage()).append(",");
        }
        ValidationErrorDTO response = new ValidationErrorDTO();
        response.setTimeStamp(new Date());
        response.setResponseCode(400);
        response.setResponseDescription("Validation Fail!");
        response.setErrors(splitToMap(sb.substring(0, sb.length() - 1)));
        return response;
    }

    /**
     * <h2>splitToMap</h2>
     * <p>
     * split to map
     * </p>
     * 
     * @param str
     * @return Map<String, String>
     */
    public Map<String, String> splitToMap(String str) {
        return Splitter.on(",").withKeyValueSeparator(":").split(str);
    }
}
