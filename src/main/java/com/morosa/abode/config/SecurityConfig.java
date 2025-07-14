/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author plutus
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ new syntax
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/nurses/apply",
                        "/api/nurses/search",
                        "/api/ratings",
                        "/api/admin/nurse-applications/*",
                        "/")
                .permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable()); // remove default UI

        return http.build();
    }
}
