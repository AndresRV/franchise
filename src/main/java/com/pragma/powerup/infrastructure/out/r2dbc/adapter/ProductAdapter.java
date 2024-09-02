package com.pragma.powerup.infrastructure.out.r2dbc.adapter;

import com.pragma.powerup.domain.model.Product;
import com.pragma.powerup.domain.spi.IProductPersistencePort;
import com.pragma.powerup.infrastructure.exception.CustomException;
import com.pragma.powerup.infrastructure.exception.ExceptionResponseEnum;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IProductEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll().map(productEntityMapper::toProduct);
    }

    @Override
    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ExceptionResponseEnum.PRODUCT_NOT_FOUND.getMessage())))
                .map(productEntityMapper::toProduct);
    }

    @Override
    public Mono<Void> saveProduct(Product product) {
        return productRepository.save(productEntityMapper.toEntity(product)).then();
    }

    @Override
    public Mono<Product> getProductWithMaxStockByBranchId(Long branchId) {
        return productRepository.findProductWithMaxStockByBranchId(branchId).map(productEntityMapper::toProduct);
    }

    @Override
    public Mono<Void> removeProductByIdAndBranchId(Long id, Long branchId) {
        return productRepository.deleteByIdAndBranchId(id, branchId);
    }
}
