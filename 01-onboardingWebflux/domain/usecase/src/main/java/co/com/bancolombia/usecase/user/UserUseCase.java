package co.com.bancolombia.usecase.user;

import co.com.bancolombia.enums.TechnicalMessage;
import co.com.bancolombia.exception.BusinessException;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserCache;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.model.user.gateways.UserWebClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    private final UserWebClient userWebClient;
    private final UserCache userCache;

    //TODO: ORGANIZAR EXCEPCIONES

    public Mono<User> saveUser(Long id) {
        //TODO: NO SE PUEDE BUSCAR POR ID, SE DEBE BUSCAR POR CORREO POR EJEMPLO PERO ENTONCES PRIMERO HAY QUE
        // BUSCARLO EN INTERNET ANTES DE GUARDARLO - PARA ENVIAR A GUARDAR EL ID DEBE IR NULL SI O SI
        return userRepository.getUserById(id)
                .switchIfEmpty(saveUserFromWeb(id));
    }

    public Mono<User> getUserById(Long id) {
        return userCache.findById(id.toString())
                .switchIfEmpty(getUserByIdFromDb(id));
    }

    public Flux<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Mono<User> getUserByFirstName(String firstName) {
        return userRepository.getUserByFirstName(firstName)
                .switchIfEmpty(Mono.error(new Exception("El usuario no existe")));
    }

    private Mono<User> saveUserFromWeb(Long id) {
        return userWebClient.getUserById(id)
                .switchIfEmpty(Mono.error(new Exception("No se puede encontrar el usuario a guardar")))
                .flatMap(userRepository::saveUser);
    }

    private Mono<User> getUserByIdFromDb(Long id) {
        return userRepository.getUserById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException(TechnicalMessage.INVALID_ID))))
                .flatMap(user -> userCache.save(user.getId().toString(), user, 60000L).thenReturn(user));
    }
}
