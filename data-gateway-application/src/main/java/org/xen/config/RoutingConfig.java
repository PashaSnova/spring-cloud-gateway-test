package org.xen.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoutingConfig {

    private final TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;

    @Bean
    RouteLocator route(RouteLocatorBuilder builder) {
        return builder.routes()
          .route(
            "welcome",
            route -> route
              .path("/welcome")
              .filters(
                f -> f.filters(tokenRelayGatewayFilterFactory.apply())
              )
              .uri("http://127.0.0.1:8080")
          )
          .build();
    }
}
