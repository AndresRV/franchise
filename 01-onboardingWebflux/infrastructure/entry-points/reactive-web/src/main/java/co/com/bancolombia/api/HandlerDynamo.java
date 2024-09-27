package co.com.bancolombia.api;

import co.com.bancolombia.model.information.Information;
import co.com.bancolombia.model.information.InformationDto;
import co.com.bancolombia.usecase.information.InformationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerDynamo {
    private  final InformationUseCase informationUseCase;

    public Mono<ServerResponse> saveInformationUser(ServerRequest serverRequest) {
        /*Mono<User> userMono = userUseCase.saveUser(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMono, User.class);*/
        Mono<Information> informationMono = informationUseCase.saveInformationUser();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(informationMono, Information.class);
    }
}
