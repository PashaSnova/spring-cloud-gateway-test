package org.xen.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain security(ServerHttpSecurity http) {
        return http
          .authorizeExchange()
          .anyExchange()
          .authenticated()
          .and()
          .oauth2ResourceServer()
          .jwt()
          .and()
          .and()
          .build();
    }
}
