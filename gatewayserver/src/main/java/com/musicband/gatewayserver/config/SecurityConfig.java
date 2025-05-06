package com.musicband.gatewayserver.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${authserver_url}")
    private String authServerUrl;


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("/musicband/authserver/**").permitAll()
                        .anyExchange().hasAnyRole("USER","ADMIN")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {
                            jwt.jwtDecoder(jwtDecoder());
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                        })
                );
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return NimbusReactiveJwtDecoder.withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS384)
                .build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        return new Converter<Jwt, Mono<AbstractAuthenticationToken>>() {
            @Override
            public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
                String jti = jwt.getClaim("jti").toString();
                String role = jwt.getClaim("role").toString();
                System.out.println(role);
                System.out.println(jti);
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                return isTokenBlacklisted(jti).flatMap(blacklisted ->{
                    if(blacklisted){
                        return Mono.error(new RuntimeException("Token is blacklisted"));
                    }
                    return Mono.just(new JwtAuthenticationToken(jwt, authorities));
                });
            }
        };
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    private Mono<Boolean> isTokenBlacklisted(String jti) {
        return webClient()
                .get()
                .uri(authServerUrl, jti)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(false);
                });
    }
}

