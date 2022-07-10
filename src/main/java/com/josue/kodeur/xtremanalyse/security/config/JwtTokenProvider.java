package com.josue.kodeur.xtremanalyse.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.*;
import static java.util.Arrays.stream;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.josue.kodeur.xtremanalyse.security.entities.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JosueKodeur
 */

@Component
public class JwtTokenProvider {

    public String generateToken(UserPrincipal user){
        String[] claims = getClaimsFromUser(user);
        Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET.getBytes());
        return JWT.create()
                .withIssuer(APPLICATION_NAME)
                .withSubject(user.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .sign(algorithm);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
        );
        usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthToken;
    }

    public String getSubject(String token){
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token).getSubject();
    }

    public boolean isTokenValid(String username, String token){
        JWTVerifier verifier = getVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private JWTVerifier getVerifier(){
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET.getBytes());
            verifier = JWT.require(algorithm).withIssuer(APPLICATION_NAME).build();
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token non valide");
        }
        return verifier;
    }

    private String[] getClaimsFromToken(String token){
        JWTVerifier verifier = getVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    public List<GrantedAuthority> getAuthorities(String token){
        String[] roles  = getClaimsFromToken(token);
        return stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private String[] getClaimsFromUser(UserPrincipal user){
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority: user.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }

        return authorities.toArray(new String[0]);
    }
}
