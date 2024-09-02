package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.ProductRequest;
import com.pragma.powerup.application.dto.response.ProductResponse;
import com.pragma.powerup.application.handler.IProductHandler;
import com.pragma.powerup.application.mapper.IProductRequestMapper;
import com.pragma.powerup.application.mapper.IProductResponseMapper;
import com.pragma.powerup.domain.api.IProductServicePort;
import com.pragma.powerup.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductHandler implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final IProductResponseMapper productResponseMapper;
    private final IProductRequestMapper productRequestMapper;

    @Override
    public Flux<ProductResponse> getAllProducts() {
        return productServicePort.getAllProducts().map(product -> productResponseMapper.toResponse(product));
    }

    @Override
    public Mono<Void> addProductToBranch(Long branchId, ProductRequest productRequest) {
        Product product = productRequestMapper.toProduct(productRequest);
        product.setBranchId(branchId);
        return productServicePort.addProduct(product);
    }

    @Override
    public Mono<Void> removeProductToBranch(Long id, Long branchId) {
        return productServicePort.removeProduct(id, branchId);
    }

    @Override
    public Mono<Void> updateProductStock(Long id, ProductRequest productRequest) {
        return productServicePort.getById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setStock(productRequest.getStock() != null ? productRequest.getStock() : existingProduct.getStock());
                    return productServicePort.updateProduct(existingProduct);
                });
    }

    @Override
    public Mono<Void> updateProductName(Long id, ProductRequest productRequest) {
        return productServicePort.getById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productRequest.getName() != null ? productRequest.getName() : existingProduct.getName());
                    return productServicePort.updateProduct(existingProduct);
                });
    }
}
