package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBranchServicePort {
    Flux<Branch> getAllBranches();
    Mono<Void> addBranch(Branch branch);
    Flux<Branch> getBranchesByFranchiseId(Long franchiseId);
    Mono<Branch> getById(Long id);
    Mono<Void> updateBranch(Branch branch);
}
