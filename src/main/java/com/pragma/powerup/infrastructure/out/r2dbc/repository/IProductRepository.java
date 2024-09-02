package com.pragma.powerup.infrastructure.out.r2dbc.repository;

import com.pragma.powerup.infrastructure.out.r2dbc.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
    Mono<Void> deleteByIdAndBranchId(Long id, Long branchId);
    @Query("SELECT * FROM product WHERE branch_id = :branchId ORDER BY stock DESC LIMIT 1")
    Mono<ProductEntity>findProductWithMaxStockByBranchId(Long branchId);
}
