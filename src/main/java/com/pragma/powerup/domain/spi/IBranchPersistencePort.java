package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBranchPersistencePort {
    Flux<Branch> getAllBranches();
    Mono<Branch> getById(Long id);
    Mono<Void> saveBranch(Branch branch);
}
