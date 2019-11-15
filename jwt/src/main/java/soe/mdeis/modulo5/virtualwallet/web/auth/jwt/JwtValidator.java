package soe.mdeis.modulo5.virtualwallet.web.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtValidator {
    private final JwtUtils jwtUtils;

    @Autowired
    public JwtValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = jwtUtils.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = jwtUtils.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
