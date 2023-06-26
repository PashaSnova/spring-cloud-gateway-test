package org.xen.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Bean
    SecurityWebFilterChain secure(ServerHttpSecurity http) {
        return http
          .authorizeExchange()
          .pathMatchers("/login", "/loggedOut")
          .permitAll()
          .anyExchange()
          .authenticated()
          .and()
          .oauth2Client()
          .and()
          .oauth2Login()
          .and()
          .logout()
          .logoutSuccessHandler(keycloakLogoutHandler())
          .and()
          .build();
    }

    @Bean
    public ServerLogoutSuccessHandler keycloakLogoutHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
          new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8090/loggedOut");
        return oidcLogoutSuccessHandler;
    }
}
