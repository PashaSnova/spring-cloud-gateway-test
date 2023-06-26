package org.xen.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class LogoutController {

    @Bean
    RouterFunction<ServerResponse> logout() {
        return route(
          GET("/loggedOut"),
          request -> ServerResponse.ok()
            .bodyValue("You are logged out")
        );
    }
}
