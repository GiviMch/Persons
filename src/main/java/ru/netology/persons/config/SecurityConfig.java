package ru.netology.persons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // доступ без авторизации
                        .requestMatchers("/persons/by-city").permitAll()
                        // все остальные только после логина
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults()) // стандартная форма логина
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}