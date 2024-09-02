package com.pragma.powerup.infrastructure.input;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.request.FranchiseRequest;
import com.pragma.powerup.application.dto.response.BranchResponse;
import com.pragma.powerup.application.dto.response.FranchiseResponse;
import com.pragma.powerup.application.handler.IBranchHandler;
import com.pragma.powerup.application.handler.IFranchiseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseFunctionHandler {
    private final IFranchiseHandler franchiseHandler;
    private static final String ID = "id";

    public Mono<ServerResponse> getAllBranches() {
        Flux<FranchiseResponse> franchiseResponseFlux = franchiseHandler.getAllFranchises();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(franchiseResponseFlux, FranchiseResponse.class);
    }

    public Mono<ServerResponse> addFranchise(ServerRequest request) {
        return request.bodyToMono(FranchiseRequest.class)
                .flatMap(franchiseRequest -> franchiseHandler.addFranchise(franchiseRequest))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateFranchiseName(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable(ID));
        return request.bodyToMono(FranchiseRequest.class)
                .flatMap(franchiseRequest -> franchiseHandler.updateFranchiseName(id, franchiseRequest))
                .then(ServerResponse.noContent().build());
    }
}
