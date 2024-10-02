package co.com.bancolombia.usecase.user;

import co.com.bancolombia.enums.TechnicalMessage;
import co.com.bancolombia.exception.BusinessException;
import co.com.bancolombia.exception.TechnicalException;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserCache;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.model.user.gateways.UserSerialize;
import co.com.bancolombia.model.user.gateways.UserWebClient;
import co.com.bancolombia.model.user.gateways.UserQueueClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    private final UserWebClient userWebClient;
    private final UserCache userCache;
    private final UserQueueClient userQueueClient;
    private final UserSerialize userSerialize;

    private static final Long EXPIRATION_CACHE = 60000L;

    public Mono<User> saveUser(Long id) {
        return userRepository.getUserById(id)
                .switchIfEmpty(saveUserFromWeb(id));
                //.switchIfEmpty(Mono.defer(() -> saveUserFromWeb(id)));
    }

    public Mono<User> getUserById(Long id) {
        return userCache.findById(id.toString())
                .onErrorResume(error -> getUserByIdFromDb(id))
                .switchIfEmpty(getUserByIdFromDb(id));
    }

    public Flux<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Mono<User> getUserByFirstName(String firstName) {
        return userRepository.getUserByFirstName(firstName)
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.INVALID_NAME)));
    }

    private Mono<User> saveUserFromWeb(Long id) {
        return userWebClient.getUserById(id)
                .onErrorResume(error -> {
                    System.out.println("existe un error");
                    return Mono.error(new TechnicalException(TechnicalMessage.GENERIC_ERROR));

                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.INVALID_USER)))
                .flatMap(user ->
                    userRepository.saveUser(user)
                            .flatMap(savedUSer ->
                                userQueueClient.send(userSerialize.serializeUSer(savedUSer))
                                        .thenReturn(savedUSer)
                            )
                ).doOnSubscribe(subs -> System.out.println("llego al fin"));
    }

    private Mono<User> getUserByIdFromDb(Long id) {
        return userRepository.getUserById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException(TechnicalMessage.INVALID_ID))))
                .flatMap(user -> userCache.save(user.getId().toString(), user, EXPIRATION_CACHE)
                        .onErrorReturn(user)
                        .thenReturn(user));
    }
}
