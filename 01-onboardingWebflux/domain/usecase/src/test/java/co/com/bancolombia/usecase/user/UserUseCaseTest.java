package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserCache;
import co.com.bancolombia.model.user.gateways.UserQueueClient;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.model.user.gateways.UserSerialize;
import co.com.bancolombia.model.user.gateways.UserWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserWebClient userWebClient;
    @Mock
    private UserCache userCache;
    @Mock
    private UserQueueClient userQueueClient;
    @Mock
    private UserSerialize userSerialize;

    private UserUseCase userUseCase;
    private User userMock;

    @BeforeEach
    public void setUp() {
        userUseCase= new UserUseCase(userRepository,
                userWebClient,
                userCache,
                userQueueClient,
                userSerialize);

        userMock = User.builder()
                .id(1L)
                .email("a@s.co")
                .firstName("qwer")
                .lastName("asdf")
                .build();
    }

    @Test
    void shouldGetUserWhenFoundInRepository() {
        given(userRepository.getUserById(1L))
                .willReturn(Mono.just(userMock));

        Mono<User> userMono = userUseCase
                .saveUser(1L);

        StepVerifier.create(userMono)
                .expectNextMatches(userResponse -> {
                    assertEquals(1L, userResponse.getId());
                    assertEquals("a@s.co", userResponse.getEmail());
                    assertEquals("qwer", userResponse.getFirstName());
                    assertEquals("asdf", userResponse.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userWebClient, never()).getUserById(anyLong());
        verify(userRepository).getUserById(anyLong());
    }

    @Test
    void shouldSaveUserWhenNotFoundInRepository() {
        given(userRepository.getUserById(1L))
                .willReturn(Mono.empty());

        given(userWebClient.getUserById(1L))
                .willReturn(Mono.just(userMock));

        given(userRepository.saveUser(userMock))
                .willReturn(Mono.just(userMock));

        given(userSerialize.serializeUSer(userMock))
                .willReturn("userJson");

        given(userQueueClient.send("userJson"))
                .willReturn(Mono.just("userJson"));

        Mono<User> userMono = userUseCase
                .saveUser(1L);

        StepVerifier.create(userMono)
                .expectNextMatches(userResponse -> {
                    assertEquals(1L, userResponse.getId());
                    assertEquals("a@s.co", userResponse.getEmail());
                    assertEquals("qwer", userResponse.getFirstName());
                    assertEquals("asdf", userResponse.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userRepository).getUserById(anyLong());
        verify(userWebClient).getUserById(anyLong());
        verify(userRepository).saveUser(any(User.class));
        verify(userSerialize).serializeUSer(any(User.class));
        verify(userQueueClient).send(anyString());
    }

    @Test
    void shouldGetUserByIdFromCache() {
        given(userCache.findById("1"))
                .willReturn(Mono.just(userMock));

        Mono<User> userMono = userUseCase
                .getUserById(1L);

        StepVerifier.create(userMono)
                .expectNextMatches(userResponse -> {
                    assertEquals(1L, userResponse.getId());
                    assertEquals("a@s.co", userResponse.getEmail());
                    assertEquals("qwer", userResponse.getFirstName());
                    assertEquals("asdf", userResponse.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userCache).findById("1");
    }

    @Test
    void shouldGetUserByIdFromDb() {
        given(userCache.findById("1"))
                .willReturn(Mono.empty());

        given(userRepository.getUserById(1L))
                .willReturn(Mono.just(userMock));

        given(userCache.save("1", userMock, 60000L))
                .willReturn(Mono.just(userMock));

        Mono<User> userMono = userUseCase
                .getUserById(1L);

        StepVerifier.create(userMono)
                .expectNextMatches(userResponse -> {
                    assertEquals(1L, userResponse.getId());
                    assertEquals("a@s.co", userResponse.getEmail());
                    assertEquals("qwer", userResponse.getFirstName());
                    assertEquals("asdf", userResponse.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userCache).findById(anyString());
        verify(userRepository).getUserById(anyLong());
        verify(userCache).save(anyString(), any(User.class), anyLong());
    }

    @Test
    void shouldGetAllUsers() {
        given(userRepository.getAllUsers())
                .willReturn(Flux.just(userMock, userMock));

        Flux<User> userFlux = userUseCase
                .getAllUsers();

        StepVerifier.create(userFlux)
                .expectNext(userMock, userMock)
                .verifyComplete();

        verify(userRepository).getAllUsers();
    }

    @Test
    void shouldGetGetUserByFirstName() {
        given(userRepository.getUserByFirstName("qwer"))
                .willReturn(Mono.just(userMock));

        Mono<User> userMono = userUseCase
                .getUserByFirstName("qwer");

        StepVerifier.create(userMono)
                .expectNextMatches(userResponse -> {
                    assertEquals(1L, userResponse.getId());
                    assertEquals("a@s.co", userResponse.getEmail());
                    assertEquals("qwer", userResponse.getFirstName());
                    assertEquals("asdf", userResponse.getLastName());
                    return true;
                })
                .verifyComplete();

        verify(userRepository).getUserByFirstName("qwer");
    }
}