package medium.vue.api.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import medium.vue.api.bl.service.dto.LoginDTO;
import medium.vue.api.config.TokenConfig;

/**
 * <h2> AuthorizationInterceptor Class</h2>
 * <p>
 * Process for Displaying AuthorizationInterceptor
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
    /**
     * <h2> tokenConfig</h2>
     * <p>
     * tokenConfig
     * </p>
     */
    @Autowired
    private TokenConfig tokenConfig;
    
    /**
     * <h2> preHandle</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = tokenConfig.getBearerToken(request);
        if (token == null) {
            tokenConfig.getLoginFailResponse(response, HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        LoginDTO loginUser = null;
        if (tokenConfig.isValidToken(token)) {
            loginUser = tokenConfig.getLoginDTO(token);
        }
        if (loginUser == null) {
            tokenConfig.getLoginFailResponse(response, HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            return true;
        }
    }
}
