package org.example.apispring.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.apispring.service.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private final String jwtSecret = "your_secret_key";
    private final long jwtExpirationMs = 86400000;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtUtils(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    public String generateJwtToken(Authentication auth, String id) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        // Lấy role dưới dạng List<String>
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        claims.put("userId", id);

        return Jwts.builder()
                .claim("userId", id)
                .setClaims(claims)
                .setSubject(auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
