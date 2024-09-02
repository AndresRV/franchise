package com.pragma.powerup.infrastructure.input;

import com.pragma.powerup.application.dto.request.ProductRequest;
import com.pragma.powerup.application.dto.response.ProductResponse;
import com.pragma.powerup.application.handler.IProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductFunctionHandler {
    private final IProductHandler productHandler;
    private static final String ID = "id";
    private static final String ID_BRANCH = "idBranch";

    public Mono<ServerResponse> getAllProducts() {
        Flux<ProductResponse> productResponseFlux = productHandler.getAllProducts();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productResponseFlux, ProductResponse.class);
    }

    public Mono<ServerResponse> addProductToBranch(ServerRequest request) {
        Long branchId = Long.valueOf(request.pathVariable(ID_BRANCH));
        return request.bodyToMono(ProductRequest.class)
                .flatMap(productRequest -> productHandler.addProductToBranch(branchId, productRequest))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> removeProductToBranch(ServerRequest request) {
            Long id = Long.valueOf(request.pathVariable(ID));
            Long branchId = Long.valueOf(request.pathVariable(ID_BRANCH));
            return productHandler.removeProductToBranch(id, branchId).then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateProductStock(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable(ID));
        return request.bodyToMono(ProductRequest.class)
                .flatMap(productRequest -> productHandler.updateProductStock(id, productRequest))
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateProductName(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable(ID));
        return request.bodyToMono(ProductRequest.class)
                .flatMap(productRequest -> productHandler.updateProductName(id, productRequest))
                .then(ServerResponse.noContent().build());
    }
}