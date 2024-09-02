package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IBranchServicePort;
import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.spi.IBranchPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BranchUseCase implements IBranchServicePort {
    private final IBranchPersistencePort branchPersistencePort;

    public BranchUseCase(IBranchPersistencePort branchPersistencePort) {
        this.branchPersistencePort = branchPersistencePort;
    }

    @Override
    public Flux<Branch> getAllBranches() {
        return branchPersistencePort.getAllBranches();
    }

    @Override
    public Mono<Void> addBranch(Branch branch) {
        return branchPersistencePort.saveBranch(branch);
    }

    @Override
    public Mono<Branch> getById(Long id) {
        return branchPersistencePort.getById(id);
    }

    @Override
    public Mono<Void> updateBranch(Branch branch) {
        return branchPersistencePort.saveBranch(branch);
    }
}
