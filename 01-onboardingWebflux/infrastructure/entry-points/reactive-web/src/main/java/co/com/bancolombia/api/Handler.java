package co.com.bancolombia.api;

import co.com.bancolombia.api.response.MessageErrorResponse;
import co.com.bancolombia.enums.TechnicalMessage;
import co.com.bancolombia.exception.BusinessException;
import co.com.bancolombia.exception.TechnicalException;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private static final String ID = "id";
    private static final String NAME = "name";

    private  final UserUseCase userUseCase;

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable(ID));
        return userUseCase
                .saveUser(id)
                .flatMap(user ->
                        Mono.defer(() -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user))
                )
                .onErrorResume(BusinessException.class, error ->
                        Mono.defer(() -> ServerResponse.status(HttpStatusCode.valueOf(Integer.valueOf(error.getTechnicalMessage().getExternalCode())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(buildMessageErrorResponse(error.getTechnicalMessage())))
                )
                .onErrorResume(TechnicalException.class, error ->
                        Mono.defer(() -> ServerResponse.status(HttpStatusCode.valueOf(Integer.valueOf(error.getTechnicalMessage().getExternalCode())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(buildMessageErrorResponse(error.getTechnicalMessage())))
                );
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable(ID));
        return userUseCase
                .getUserById(id)
                .flatMap(user ->
                        Mono.defer(() -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user))
                )
                .onErrorResume(BusinessException.class, error ->
                        Mono.defer(() -> ServerResponse.status(HttpStatusCode.valueOf(Integer.valueOf(error.getTechnicalMessage().getExternalCode())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(buildMessageErrorResponse(error.getTechnicalMessage())))
                );
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        Flux<User> userFlux = userUseCase.getAllUsers();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userFlux, User.class);
    }

    public Mono<ServerResponse> getUserByFirstName(ServerRequest serverRequest) {
        String name = String.valueOf(serverRequest.pathVariable(NAME));
        return userUseCase
                .getUserByFirstName(name)
                .flatMap(user ->
                        Mono.defer(() -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(user))
                )
                .onErrorResume(BusinessException.class, error ->
                        Mono.defer(() -> ServerResponse.status(HttpStatusCode.valueOf(Integer.valueOf(error.getTechnicalMessage().getExternalCode())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(buildMessageErrorResponse(error.getTechnicalMessage())))
                );
    }

    private MessageErrorResponse buildMessageErrorResponse(TechnicalMessage technicalMessage) {
        return new MessageErrorResponse(technicalMessage.getMessage());
    }
}
