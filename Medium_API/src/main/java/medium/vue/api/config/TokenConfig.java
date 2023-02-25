package medium.vue.api.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.bl.service.dto.LoginDTO;
import medium.vue.api.bl.service.dto.LoginResponseDTO;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.UserForm;

/**
 * <h2>TokenConfig Class</h2>
 * <p>
 * Process for Displaying TokenConfig
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Component
public class TokenConfig {
    /**
     * <h2>secret</h2>
     * <p>
     * secret
     * </p>
     */
    private String secret = "H@5h_K3Y";

    /**
     * <h2>validity</h2>
     * <p>
     * validity
     * </p>
     */
    private long validity = 2880L;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>getLoginDTO</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return LoginDTO
     */
    public LoginDTO getLoginDTO(String token) {
        String email = this.getLoggedInEmail(token);
        UserForm user = userService.doGetUserByEmail(email);
        return new LoginDTO(new User(user));
    }

    /**
     * <h2>isValidToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return Boolean
     */
    public Boolean isValidToken(String token) {
        String email = this.getLoggedInEmail(token);
        UserForm userForm = this.userService.doGetUserByEmail(email);
        return (userForm != null && !isTokenExpired(token));
    }

    /**
     * <h2>generateToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return
     * @return String
     */
    public String generateToken(UserForm user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Name", user.getName());
        return doGenerateToken(claims, user.getEmail());
    }

    /**
     * <h2>getLoggedInEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return String
     */
    public String getLoggedInEmail(String token) {
        return getClaim(token, Claims::getSubject);
    }
    
    /**
     * <h2> getLoginEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return LoginDTO
     */
    public LoginDTO getLoginEmail(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String token = requestHeader.substring(7);
            return this.getLoginDTO(token);
        }
        return null;
    }

    /**
     * <h2>getClaim</h2>
     * <p>
     * 
     * </p>
     *
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     * @return T
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * <h2>getExpirationDate</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return Date
     */
    public Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * <h2>isTokenExpired</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDate(token);
        return expiration.before(new Date());
    }

    /**
     * <h2>getBearerToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return String
     */
    public String getBearerToken(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            return requestHeader.substring(7);
        }
        return null;
    }

    /**
     * <h2>getLoginFailResponse</h2>
     * <p>
     * 
     * </p>
     *
     * @param response
     * @param status
     * @throws IOException
     * @return void
     */
    public void getLoginFailResponse(HttpServletResponse response, int status) throws IOException {
        LoginResponseDTO responseDTO = null;
        if (status == HttpStatus.FORBIDDEN.value()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            responseDTO = new LoginResponseDTO(HttpStatus.FORBIDDEN.value(), "Forbidden");
        } else if (status == HttpStatus.BAD_REQUEST.value()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO = new LoginResponseDTO(HttpStatus.BAD_REQUEST.value(), "Bad Request");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseDTO = new LoginResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(new Gson().toJson(responseDTO));
        out.flush();
    }
    
    /**
     * <h2> refreshToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return String
     */
    public String refreshToken(String token) {
        Map<String, Object> claims = this.getAllClaims(token);
        return (claims == null) ? null : doGenerateToken(claims, this.getLoggedInEmail(token));
    }

    /**
     * <h2>getAllClaims</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return Claims
     */
    private Claims getAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <h2>doGenerateToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param claims
     * @param subject
     * @return
     * @return String
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (validity * 60) * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
