package com.example.apigateway.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.config.HttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OktaOAUth2WebSecurity {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

    @Primary
    @Bean
    public HttpClientProperties httpClientProperties2() {
        return new CustomHttpClientProperties();
    }

    @ConfigurationProperties(value = "spring.cloud.gateway.httpclient", ignoreInvalidFields = true)
    private static class CustomHttpClientProperties extends HttpClientProperties {

    }


}
