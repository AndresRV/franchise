package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.user.User;
import reactor.core.publisher.Mono;

public interface UserCache {
    Mono<User> save(String key, User entity, long expirationMillis);
    Mono<User> findById(String key);
}
