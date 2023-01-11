package com.appreservas.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final RsaKeyProperties rsaKeys;

    public SecurityConfig(RsaKeyProperties rsaKeys) {

        this.rsaKeys = rsaKeys;
    }

    @Bean
    public AuthenticationManager authManager(PasswordEncoder passwordEncoder){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //disable cross site scripting
                .authorizeRequests(auth -> auth
                        // cliente
                        .regexMatchers(HttpMethod.POST, "/cliente.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/cliente.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/cliente.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/cliente.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        
                        // organizador
                        .regexMatchers(HttpMethod.POST, "/organizador.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/organizador.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/organizador.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/organizador.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")

                        // evento
                        .regexMatchers(HttpMethod.POST, "/evento.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/evento.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/evento.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/evento.*").authenticated()

                        // lugar
                        .regexMatchers(HttpMethod.POST, "/lugar.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/lugar.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/lugar.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/lugar.*").authenticated()
                        
                        // reserva
                        .regexMatchers(HttpMethod.POST, "/reserva.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/reserva.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/reserva.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/reserva.*").authenticated()

                        // reservalugar
                        .regexMatchers(HttpMethod.POST, "/reservalugar.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/reservalugar.*").hasAnyAuthority("SCOPE_user", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/reservalugar.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/reservalugar.*").authenticated()

                        // sala
                        .regexMatchers(HttpMethod.POST, "/sala.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/sala.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/sala.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/sala.*").authenticated()
                        
                        // sessao
                        .regexMatchers(HttpMethod.POST, "/sessao.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.PUT, "/sessao.*").hasAnyAuthority("SCOPE_org", "SCOPE_admin")
                        .regexMatchers(HttpMethod.DELETE, "/sessao.*").hasAnyAuthority("SCOPE_admin")
                        .regexMatchers(HttpMethod.GET, "/sessao.*").authenticated()
                        
                        // docs
                        .regexMatchers("/swagger-ui.*", "/api-docs").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //disable session manager
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey
                .Builder(rsaKeys.publicKey())
                .privateKey(rsaKeys.privateKey())
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
