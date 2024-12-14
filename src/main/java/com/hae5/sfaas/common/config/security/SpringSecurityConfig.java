package com.hae5.sfaas.common.config.security;

import com.hae5.sfaas.common.config.security.filter.CustomAccessDeniedHandler;
import com.hae5.sfaas.common.config.security.filter.CustomAuthenticationEntryPoint;
import com.hae5.sfaas.common.config.security.filter.ExceptionHandlerFilter;
import com.hae5.sfaas.common.config.security.filter.JwtAuthenticationFilter;
import com.hae5.sfaas.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static com.hae5.sfaas.user.enums.UserRole.ADMIN;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfig {

    @Value("${swagger.user}")
    private String SWAGGER_USER;

    @Value("${swagger.password}")
    private String SWAGGER_PASSWORD;

    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtProvider jwtProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] PERMIT_URL = {
        "/api/v1/auth/login", "/api/v1/auth/register"
    };

    private static final String[] ADMIN_URL = {
        "/api/v1/user/{userId}", "/api/v1/user/role/{userId}"
    };

    private static final String[] SWAGGER_URL = {"/swagger-ui/**", "/v3/api-docs/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(config -> config.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(PERMIT_URL).permitAll()
                        .requestMatchers(ADMIN_URL).hasAuthority(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .exceptionHandling((config) -> config.authenticationEntryPoint(customAuthenticationEntryPoint))
                .exceptionHandling((config) -> config.accessDeniedHandler(customAccessDeniedHandler))
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(SWAGGER_URL)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user =
                User.withUsername(SWAGGER_USER)
                        .password(passwordEncoder().encode(SWAGGER_PASSWORD))
                        .roles("SWAGGER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


}
