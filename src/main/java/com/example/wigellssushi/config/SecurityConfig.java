package com.example.wigellssushi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v3/user/**").hasAuthority("USER")
                .requestMatchers("/api/v3/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
        );
        */
        http.authorizeHttpRequests(authorize -> authorize
            .anyRequest().permitAll()
        );
        http
            .csrf().disable();

        return http.build();
    }
}

