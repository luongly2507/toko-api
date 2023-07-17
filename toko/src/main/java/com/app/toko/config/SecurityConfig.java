package com.app.toko.config;

import static org.springframework.http.HttpMethod.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
