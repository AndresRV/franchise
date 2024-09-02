package com.pragma.powerup.infrastructure.out.r2dbc.repository;

import com.pragma.powerup.infrastructure.out.r2dbc.entity.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IBranchRepository extends ReactiveCrudRepository<BranchEntity, Long> {
    Flux<BranchEntity> findAllByFranchiseId(Long franchiseId);
}
