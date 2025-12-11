package com.schoolevents.schoolevents_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .formLogin(form -> form.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/comments/all").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/comments/by_event/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/comments/by_id/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/comments/by_user/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/comments/by_event/").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/comments/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comments/update/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comments/delete/").permitAll()


                        .requestMatchers(HttpMethod.GET, "/prendas/filtrarestado").permitAll()
                        .requestMatchers(HttpMethod.GET, "/prendas/filtrarguardado").permitAll()
                        .requestMatchers(HttpMethod.POST, "/prendas/crearPrenda").permitAll()
                        .requestMatchers(HttpMethod.POST, "/marcas/crearMarca").permitAll()
                        .requestMatchers(HttpMethod.POST, "/colores/crearColores").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/prendas/prendaspopulares").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/intercambiosactivos").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
