package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.ProductRequest;
import com.pragma.powerup.application.dto.response.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductHandler {
    Flux<ProductResponse> getAllProducts();
    Mono<Void> addProductToBranch(Long branchId, ProductRequest productRequest);
    Mono<Void> removeProductToBranch(Long id, Long branchId);
    Mono<Void> updateProductStock(Long id, ProductRequest productRequest);
    Mono<Void> updateProductName(Long id, ProductRequest productRequest);
}
