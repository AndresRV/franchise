package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductPersistencePort {
    Flux<Product> getAllProducts();
    Mono<Product> getById(Long id);
    Mono<Void> saveProduct(Product product);
    Mono<Product> getProductWithMaxStockByBranchId(Long branchId);
    Mono<Void> removeProductByIdAndBranchId(Long id, Long branchId);
}

