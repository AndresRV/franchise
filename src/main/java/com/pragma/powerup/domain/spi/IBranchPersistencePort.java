package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBranchPersistencePort {
    Flux<Branch> getAllBranches();
    Flux<Branch> getBranchesByFranchiseId(Long franchiseId);
    Mono<Branch> getById(Long id);
    Mono<Void> saveBranch(Branch branch);
}
