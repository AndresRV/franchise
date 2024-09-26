package co.com.bancolombia.model.user.gateways;

import reactor.core.publisher.Mono;

public interface UserQueueClient {
    Mono<String> send(String message);
}
