package com.example.user.auth.server.http.router;

import com.example.user.auth.server.http.handler.UserAuthHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

class UserAuthRouterTest {

    @Mock
    private UserAuthHandler userAuthHandler;

    @InjectMocks
    private UserAuthRouter userAuthRouter;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RouterFunction<ServerResponse> routerFunction = userAuthRouter.route(userAuthHandler);
        this.webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    void testUserAuthRoute() {
        // Mock the response from UserAuthHandler for status code 200
        Mockito.when(userAuthHandler.getUserResponse(Mockito.any()))
                .thenReturn(ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Success: User authorized")));

        // Use WebTestClient to test the /userAuth endpoint for status code 200
        webTestClient.get()
                .uri("/userAuth")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class).isEqualTo("Success: User authorized");

        // Mock the response for userAuthHandler.getUserResponse for 401 Unauthorized
        Mockito.when(userAuthHandler.getUserResponse(Mockito.any(ServerRequest.class)))
                .thenReturn(ServerResponse.status(401)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Unauthorized: User not authorized")));

        // Use WebTestClient to test the /userAuth endpoint for status code 401 Unauthorized
        webTestClient.get()
                .uri("/userAuth")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);

    }


}