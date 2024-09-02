package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IFranchiseServicePort;
import com.pragma.powerup.domain.model.Franchise;
import com.pragma.powerup.domain.spi.IFranchisePersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FranchiseUseCase implements IFranchiseServicePort {
    private final IFranchisePersistencePort franchisePersistencePort;

    public FranchiseUseCase(IFranchisePersistencePort franchisePersistencePort) {
        this.franchisePersistencePort = franchisePersistencePort;
    }

    @Override
    public Flux<Franchise> getAllFranchises() {
        return franchisePersistencePort.getAllFranchises();
    }

    @Override
    public Mono<Void> addFranchise(Franchise franchise) {
        return franchisePersistencePort.saveFranchise(franchise);
    }

    @Override
    public Mono<Franchise> getById(Long id) {
        return franchisePersistencePort.getById(id);
    }

    @Override
    public Mono<Void> updateFranchise(Franchise franchise) {
        return franchisePersistencePort.saveFranchise(franchise);
    }
}
