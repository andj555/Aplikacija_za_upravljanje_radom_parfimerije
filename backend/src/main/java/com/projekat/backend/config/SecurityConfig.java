/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projekat.backend.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author Anđelija
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthentificationFilter jwtAuthentificationFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/proizvodi/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/porudzbine/poruci").authenticated()
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/porudzbine/korisnik/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/poreske-stope/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/otpremnice/*/pdf").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/otpremnice").authenticated()   
                    
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/kategorije-mirisa/**").permitAll()   
                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/porudzbine/**").hasRole("ZAPOSLENI")     
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/proizvodi/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/proizvodi/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/proizvodi/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/korisnici").hasRole("ZAPOSLENI")
                .requestMatchers("/api/korisnici/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/poreske-stope/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/poreske-stope/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/akcije/**").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/otpremnice/generisi").hasRole("ZAPOSLENI")
                .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/porudzbine").hasRole("ZAPOSLENI")
                    .anyRequest().authenticated()
            ) .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);

            
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}