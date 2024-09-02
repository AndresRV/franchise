package com.pragma.powerup.infrastructure.input;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.response.BranchResponse;
import com.pragma.powerup.application.dto.response.BranchesWithProductsMaxStockResponse;
import com.pragma.powerup.application.handler.IBranchHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchFunctionHandler {
    private final IBranchHandler branchHandler;
    private static final String ID = "id";
    private static final String ID_FRANCHISE = "idFranchise";

    public Mono<ServerResponse> getAllBranches() {
        Flux<BranchResponse> branchResponseFlux = branchHandler.getAllBranches();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(branchResponseFlux, BranchResponse.class);
    }

    public Mono<ServerResponse> addBranchToFranchise(ServerRequest request) {
        Long franchiseId = Long.valueOf(request.pathVariable(ID_FRANCHISE));
        return request.bodyToMono(BranchRequest.class)
                .flatMap(branchRequest -> branchHandler.addBranchToFranchise(franchiseId, branchRequest))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getProductsWithMaxStockByFranchise(ServerRequest request) {
        Long franchiseId = Long.valueOf(request.pathVariable(ID_FRANCHISE));
        Flux<BranchesWithProductsMaxStockResponse> branchWithProductsMaxStocResponseFlux = branchHandler.getProductsWithMaxStockByFranchise(franchiseId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(branchWithProductsMaxStocResponseFlux, BranchesWithProductsMaxStockResponse.class);
    }

    public Mono<ServerResponse> updateBranchName(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable(ID));
        return request.bodyToMono(BranchRequest.class)
                .flatMap(branchRequest -> branchHandler.updateBranchName(id, branchRequest))
                .then(ServerResponse.noContent().build());
    }
}
