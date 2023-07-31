package com.ecommerceservice.application.configuration;


import com.ecommerceservice.application.controller.BaseController;
import com.ecommerceservice.application.entity.Role;
import com.ecommerceservice.application.filter.AuthorizationFilter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Value("${jwt.public-key}")
    private RSAPublicKey publicKey;
    @Value("${jwt.private-key}")
    private RSAPrivateKey privateKey;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security, AuthenticationManager authenticationManager,
                                                   JwtDecoder decoder) throws Exception {
        security.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/api/public/**").permitAll();
                    auth.requestMatchers(BaseController.CREATE_COUNTRY).hasRole(Role.ADMIN.name());
                    auth.requestMatchers(BaseController.UPDATE_COUNTRY).hasRole(Role.ADMIN.name());
                    auth.requestMatchers(BaseController.DELETE_COUNTRY).hasRole(Role.ADMIN.name());
                    auth.anyRequest().authenticated();
                }
        );
        security.csrf(CsrfConfigurer::disable);
        security.httpBasic(HttpBasicConfigurer::disable);
        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.addFilter(new AuthorizationFilter(authenticationManager, decoder));
        return security.build();
    }

    @Bean
    public JwtEncoder encoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> list = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(list);
    }

    @Bean
    public JwtDecoder decoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
