package com.ecp.us.config;

import com.ecp.us.constants.UserConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SecondaryRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtTokenValidator extends OncePerRequestFilter {

    public static final Logger logger = LoggerFactory.getLogger(JwtTokenValidator.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token =  request.getHeader(UserConstants.JWT_HEADER);
        if(null!=token){
            try{
                    String jwt = token.startsWith("Bearer ")? token.substring(0,7):null;
                    String secretKey = UserConstants.JWT_SECRET_KEY;
                    SecretKey secret = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
                    Claims claims = Jwts.parser().verifyWith(secret).build().parseSignedClaims(jwt).getPayload();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = String.valueOf(claims.get("authorities"));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch(Exception ex){
                logger.error("JwtTokenValidator class validate method error :{}",ex.getMessage());
                throw new BadCredentialsException("Invalid Token Recieved");
            }
        }

        filterChain.doFilter(request,response);

    }

}
