package com.iesdoctorbalmis.spring.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests(aut -> aut
            .requestMatchers(
                "/", 
                "/css/**", 
                "/img/**", 
                "/webjars/**", 
                "/h2-console/**", 
                "/public/**", 
                "/auth/**", 
                "/files/**"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .loginPage("/auth/login")
            .defaultSuccessUrl("/index", true)
            .loginProcessingUrl("/auth/login-post")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/index")
        )
        .csrf(csrf -> csrf.disable())
        .headers(headers -> 
            headers.frameOptions(frame -> frame.disable())
        );

        return http.build();
    }
}