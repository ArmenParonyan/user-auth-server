package com.example.user.auth.server.http.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UserAuthHandlerTest {

    @Mock
    private UserAuthHandler userAuthHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserAuthResponse_Success() {
        // Mock the ServerRequest
        ServerRequest serverRequest = Mockito.mock(ServerRequest.class);

        // Mock the response for userAuthHandler.getUserResponse for 200 Success
        Mockito.when(userAuthHandler.getUserResponse(Mockito.any(ServerRequest.class)))
                .thenReturn(ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Success: User authorized")));

        // Call the method with the mock
        Mono<ServerResponse> responseMono = userAuthHandler.getUserResponse(serverRequest);

        // Verify the response using StepVerifier
        StepVerifier.create(responseMono)
                .expectNextMatches(serverResponse -> {
                    // Verify status is 200 OK
                    assert serverResponse.statusCode().value() == 200;
                    return true;
                })
                .expectComplete()
                .verify();

        // Verify that the mock method was called exactly once
        Mockito.verify(userAuthHandler, Mockito.times(1))
                .getUserResponse(Mockito.any(ServerRequest.class));
    }


    @Test
    void testGetUserAuthResponse_Unauthorized() {
        // Mock the ServerRequest
        ServerRequest serverRequest = Mockito.mock(ServerRequest.class);

        // Mock the response for userAuthHandler.getUserResponse for 401 Unauthorized
        Mockito.when(userAuthHandler.getUserResponse(Mockito.any(ServerRequest.class)))
                .thenReturn(ServerResponse.status(401)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Unauthorized: User not authorized")));

        // Call the method with the mock
        Mono<ServerResponse> responseMono = userAuthHandler.getUserResponse(serverRequest);

        // Verify the response using StepVerifier
        StepVerifier.create(responseMono)
                .expectNextMatches(serverResponse -> {
                    // Verify status is 401 Unauthorized
                    assert serverResponse.statusCode().value() == 401;
                    return true;
                })
                .expectComplete()
                .verify();

        // Verify that the mock method was called exactly once
        Mockito.verify(userAuthHandler, Mockito.times(1))
                .getUserResponse(Mockito.any(ServerRequest.class));
    }

}