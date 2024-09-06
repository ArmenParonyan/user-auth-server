package com.example.user.auth.server.http.router;

import com.example.user.auth.server.http.handler.UserAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@EnableWebFlux
public class UserAuthRouter {

    @Bean
    RouterFunction<ServerResponse> route(final UserAuthHandler userAuthHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/userAuth")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userAuthHandler::getUserResponse);
    }
}
