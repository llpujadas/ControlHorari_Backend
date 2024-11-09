package com.controlHorari.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

import static com.controlHorari.Constants.Constants.BASE_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Deshabilitar CSRF para APIs
            .authorizeRequests()
            .requestMatchers(BASE_URL + "/auth/signup", BASE_URL + "/auth/login").permitAll()  // Permitir acceso público a /auth/signup y /auth/login
            .anyRequest().authenticated()  // Requiere autenticación para cualquier otro endpoint
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // No se usan sesiones, es stateless
            .and()
            .authenticationProvider(authenticationProvider)  // Proveedor de autenticación personalizado
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Agregar el filtro JWT antes del filtro de autenticación por defecto

        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));  // Permite cualquier origen (puedes limitarlo según tus necesidades)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Permitir métodos
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));  // Permitir encabezados
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
