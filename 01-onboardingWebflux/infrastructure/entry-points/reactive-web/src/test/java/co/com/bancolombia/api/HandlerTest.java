package co.com.bancolombia.api;

import co.com.bancolombia.api.response.MessageErrorResponse;
import co.com.bancolombia.enums.TechnicalMessage;
import co.com.bancolombia.exception.BusinessException;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.user.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ContextConfiguration(classes = {RouterRest.class, Handler.class})
class HandlerTest {
    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    @MockBean
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .build();
    }

    @Test
    void shouldSaveUser() {
        given(userUseCase.saveUser(anyLong()))
                .willReturn(Mono.just(buildUser()));

        webTestClient.post()
                .uri("/api/user/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class);
    }

    @Test
    void shouldGetUserById() {
        given(userUseCase.getUserById(anyLong()))
                .willReturn(Mono.just(buildUser()));

        webTestClient.get()
                .uri("/api/user/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class);

    }

    @Test
    void shouldReturnErrorWhenUserNotFound() {
        given(userUseCase.getUserById(anyLong()))
                .willReturn(Mono.error(new BusinessException(TechnicalMessage.INVALID_ID)));

        webTestClient.get()
                .uri("/api/user/1")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(MessageErrorResponse.class);
    }


    @Test
    void shouldGetAllUsers() {
        given(userUseCase.getAllUsers())
                .willReturn(Flux.just(buildUser(), buildUser())); // Retorna un Flux de usuarios

        webTestClient.get()
                .uri("/api/user")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(User.class);
    }

    @Test
    void shouldGetUserByFirstName() {
        given(userUseCase.getUserByFirstName(anyString()))
                .willReturn(Mono.just(buildUser()));

        webTestClient.get()
                .uri("/api/user/name/anyString")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class);
    }

    private User buildUser() {
        return User.builder()
                .id(1L)
                .email("a@s.c")
                .firstName("qwer")
                .lastName("asdf")
                .build();
    }
}