package br.com.sabesp.cco.authentication.config;

import br.com.sabesp.cco.authentication.service.AuthenticationProviderService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


/**
 *
 * @author muriel.carvalho@cast.com.br
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProviderService authenticationProviderService;

    private final JwtFilter jwtFilter;

    public SecurityConfig(AuthenticationProviderService authenticationProviderService, JwtFilter jwtFilter) {
        this.authenticationProviderService = authenticationProviderService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.contentSecurityPolicy(
                                cps -> cps.policyDirectives("default-src 'self'; " +
                                        "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                                        "style-src 'self' 'unsafe-inline'; " +
                                        "img-src 'self' data:; " +
                                        "frame-src 'self'; " +
                                        "connect-src 'self'; " +
                                        " base-uri 'self'; " +
                                        "font-src 'self' https: data:;form-action 'self';" +
                                        "frame-ancestors 'self'; " +
                                        "object-src 'none'; " +
                                        "upgrade-insecure-requests")
                        )) // remover o bloco de comentario em caso de rodar o banco h2 local
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/sem-permissao").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/listar-usuarios").permitAll();
                    authorize.requestMatchers(PathRequest.toH2Console()).permitAll();
                    authorize.anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProviderService);
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

}
