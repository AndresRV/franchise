package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFranchisePersistencePort {
    Flux<Franchise> getAllFranchises();
    Mono<Franchise> getById(Long id);
    Mono<Void> saveFranchise(Franchise franchise);
}
