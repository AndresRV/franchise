package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductServicePort {
    Flux<Product> getAllProducts();
    Mono<Void> addProduct(Product product);
    Mono<Void> removeProduct(Long id, Long branchId);
    Mono<Product> getProductWithMaxStockByBranchId(Long branchId);
    Mono<Product> getById(Long id);
    Mono<Void> updateProduct(Product product);
}
