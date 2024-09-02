package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IProductServicePort;
import com.pragma.powerup.domain.model.Product;
import com.pragma.powerup.domain.spi.IProductPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productPersistencePort.getAllProducts();
    }

    @Override
    public Mono<Void> addProduct(Product product) {
        return productPersistencePort.saveProduct(product);
    }

    @Override
    public Mono<Void> removeProduct(Long id, Long branchId) {
        return productPersistencePort.removeProductByIdAndBranchId(id, branchId);
    }

    @Override
    public Mono<Product> getProductWithMaxStockByBranchId(Long branchId) {
        return productPersistencePort.getProductWithMaxStockByBranchId(branchId);
    }

    @Override
    public Mono<Product> getById(Long id) {
        return productPersistencePort.getById(id);
    }

    @Override
    public Mono<Void> updateProduct(Product product) {
        return productPersistencePort.saveProduct(product);
    }
}
