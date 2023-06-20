package org.xen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain secure(ServerHttpSecurity http) {
        return http
          .authorizeExchange()
          .pathMatchers("/login/**")
          .permitAll()
          .anyExchange()
          .authenticated()
          .and()
          .oauth2Client()
          .and()
          .oauth2Login()
          .and()
          .logout()
          .and()
          .build();
    }
}
