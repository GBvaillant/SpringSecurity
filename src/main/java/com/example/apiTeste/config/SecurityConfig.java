package com.example.apiTeste.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll() // Libera todos os endpoints que começam com /public/
                                .requestMatchers(HttpMethod.POST, "/**").permitAll() // Libera todos os endpoints que começam com /public/
                                .requestMatchers("/admin/**").hasRole("ADMIN") // Restringe o acesso a /admin/ para usuários com a role ADMIN
                                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                )
                .formLogin(withDefaults()); // Habilita o login padrão do Spring Security
        return http.build();
    }
}
