package co.com.bancolombia.consumer;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserWebClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements UserWebClient {
    private final WebClient client;
    private final UserDtoMapper userDtoMapper;

    @Override
    public Mono<User> getUserById(Long id) {
        return client
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userResponse -> userDtoMapper.toUser(userResponse.getData()))
                .onErrorResume(error -> {
                    if (error instanceof WebClientResponseException.NotFound) {
                        return Mono.empty();
                    }

                    return Mono.error(error);
                });
    }
}
