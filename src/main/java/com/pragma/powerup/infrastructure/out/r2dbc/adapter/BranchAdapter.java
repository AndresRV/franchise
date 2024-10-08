package com.pragma.powerup.infrastructure.out.r2dbc.adapter;

import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.spi.IBranchPersistencePort;
import com.pragma.powerup.infrastructure.exception.CustomException;
import com.pragma.powerup.infrastructure.exception.ExceptionResponseEnum;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IBranchEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IBranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BranchAdapter implements IBranchPersistencePort {
    private final IBranchRepository branchRepository;
    private final IBranchEntityMapper branchEntityMapper;

    @Override
    public Flux<Branch> getAllBranches() {
        return branchRepository.findAll().map(branchEntityMapper::toBranch);
    }

    @Override
    public Flux<Branch> getBranchesByFranchiseId(Long franchiseId) {
        return branchRepository.findAllByFranchiseId(franchiseId).map(branchEntityMapper::toBranch);
    }

    @Override
    public Mono<Branch> getById(Long id) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ExceptionResponseEnum.BRANCH_NOT_FOUND.getMessage())))
                .map(branchEntityMapper::toBranch);
    }

    @Override
    public Mono<Void> saveBranch(Branch branch) {
        return branchRepository.save(branchEntityMapper.toEntity(branch)).then();
    }
}
