package br.com.easymoto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Cadeia de Filtros para a API REST (/api/**).
     * Prioridade 1: Stateless, desabilita CSRF e usa o filtro JWT.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Aplica esta cadeia APENAS para URLs /api/**
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Cadeia de Filtros para a aplicação Web (Thymeleaf, Swagger, etc.).
     * Prioridade 2: Fallback para tudo que não for /api/**.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Mantém o CSRF desabilitado
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso a todas as URLs públicas necessárias
                        .requestMatchers("/login", "/css/**", "/js/**", "/swagger-ui/**", "/v3/api-docs/**", "/error").permitAll()
                        // Exige autenticação para todas as outras
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Sua página de login
                        .loginProcessingUrl("/login") // URL para a qual o formulário de login faz POST
                        .defaultSuccessUrl("/web/clientes", true) // Onde ir em caso de sucesso
                        .failureUrl("/login?error=true") // Onde ir em caso de falha (senha/usuário incorreto)
                        .permitAll() // Permite acesso a todas as URLs relacionadas ao formLogin
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}