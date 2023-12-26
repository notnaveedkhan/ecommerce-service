package com.ecommerceservice.application.filter;

import com.ecommerceservice.application.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;

@Log4j2
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtDecoder decoder;

    public AuthorizationFilter(AuthenticationManager authenticationManager, JwtDecoder decoder) {
        super(authenticationManager);
        this.decoder = decoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(">>> AuthorizationFilter.doFilterInternal");

        String token = request.getHeader("Authorization");
        if (token != null && !token.isBlank()) {
            authorize(token);
        }

        super.doFilterInternal(request, response, chain);
        log.info("<<< AuthorizationFilter.doFilterInternal");
    }

    private void authorize(String token) throws JsonProcessingException {
        try {
            log.info(">>> AuthorizationFilter.authorize");
            token = extractToken(token);
            Jwt jwt = decoder.decode(token);
            String userClaim = (String) jwt.getClaims().get("user");
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            User user = mapper.readValue(userClaim, User.class);
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .toList();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("<<< AuthorizationFilter.authorize");
        } catch (BadJwtException e) {
            log.error("<<< AuthorizationFilter.authorize - Message: {} - StackTrace:{}", e.getMessage(), e.getStackTrace());
        }
    }

    private String extractToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        return token;
    }
}
