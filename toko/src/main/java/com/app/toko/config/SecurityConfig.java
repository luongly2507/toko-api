package com.app.toko.config;

import static com.app.toko.entity.Permission.ADMIN_CREATE;
import static com.app.toko.entity.Permission.ADMIN_DELETE;
import static com.app.toko.entity.Permission.ADMIN_READ;
import static com.app.toko.entity.Permission.ADMIN_UPDATE;
import static com.app.toko.entity.Permission.MANAGER_CREATE;
import static com.app.toko.entity.Permission.MANAGER_DELETE;
import static com.app.toko.entity.Permission.MANAGER_READ;
import static com.app.toko.entity.Permission.MANAGER_UPDATE;
import static com.app.toko.entity.Role.ADMIN;
import static com.app.toko.entity.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        auth -> auth.requestMatchers(GET, "/api/v1/books/**")
                                .permitAll()
                                .requestMatchers(GET, "/api/v1/categories/**")
                                .permitAll()
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("/img/**")
                                .permitAll()
                                .requestMatchers("/**", "/ws/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout -> logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (request, response,
                                         authentication) -> SecurityContextHolder
                                                .clearContext()))
                .build();

    }
}