package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFranchiseServicePort {
    Flux<Franchise> getAllFranchises();
    Mono<Void> addFranchise(Franchise franchise);
    Mono<Franchise> getById(Long id);
    Mono<Void> updateFranchise(Franchise franchise);
}
