package com.senai.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityWeb {
	private final JWTAuthEntryPoint authEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityWeb(CustomUserDetailsService customUserDetailsService, JWTAuthEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authEntryPoint)) 
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .requestMatchers("/api/auth/login/**").permitAll() 
                .requestMatchers("/api/users/change-password/**").hasAnyAuthority("ADMINISTRADOR")
                .requestMatchers("/api/users/**").hasAnyAuthority("ADMINISTRADOR")
                .requestMatchers("/api/items/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO")  
                .requestMatchers("/api/forms/**").hasAnyAuthority("ADMINISTRADOR", "FUNCIONARIO")
                .requestMatchers(HttpMethod.GET,"/api/items/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/forms/**").permitAll()
                .anyRequest().authenticated() 
            )
            .httpBasic();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors(cors -> cors.configurationSource(request -> {
            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
            corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:3000")); 
            corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); 
            corsConfig.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type")); 
            corsConfig.setAllowCredentials(true); 
            corsConfig.setMaxAge(3600L); 
            return corsConfig;
        }));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
  }