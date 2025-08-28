package ru.netology.persons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableMethodSecurity(
        securedEnabled = true,    // для @Secured
        jsr250Enabled = true,     // для @RolesAllowed
        prePostEnabled = true     // для @PreAuthorize / @PostAuthorize
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/persons/by-city").permitAll() // без авторизации
                        .anyRequest().authenticated() // всё остальное под логином
                )
                .formLogin(withDefaults())
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails reader = User.withUsername("reader")
                .password("{noop}reader123")
                .roles("READ")
                .build();

        UserDetails writer = User.withUsername("writer")
                .password("{noop}writer123")
                .roles("WRITE")
                .build();

        UserDetails deleter = User.withUsername("deleter")
                .password("{noop}deleter123")
                .roles("DELETE")
                .build();

        return new InMemoryUserDetailsManager(reader, writer, deleter);
    }
}