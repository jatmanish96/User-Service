package com.ecp.us.config;


import com.ecp.us.constants.UserConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;


public class JwtTokenProvider {

    public static String generateToken(Authentication authentication){

        SecretKey secretKey = Keys.hmacShaKeyFor(UserConstants.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().issuer("ecp").subject("JWT Token").claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority
                ).collect(Collectors.joining(","))).issuedAt(new Date()).expiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(secretKey).compact();
    }
}

