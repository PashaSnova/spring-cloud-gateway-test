package org.xen.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebServiceConfig {

    @Bean
    RouterFunction<ServerResponse> testSecuredEndpoint() {
        return route(
          GET("/welcome"),
          request -> request.principal()
            .flatMap(
              principal -> ServerResponse.ok()
                .bodyValue(
                  String.format("Hello, %s", principal.getName())
                )
              )
        );
    }
}
