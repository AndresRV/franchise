package com.pragma.powerup.infrastructure.out.r2dbc.adapter;

import com.pragma.powerup.domain.model.Franchise;
import com.pragma.powerup.domain.spi.IFranchisePersistencePort;
import com.pragma.powerup.infrastructure.exception.CustomException;
import com.pragma.powerup.infrastructure.exception.ExceptionResponseEnum;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IFranchiseEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IFranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FranchiseAdapter implements IFranchisePersistencePort {
    private final IFranchiseRepository franchiseRepository;
    private final IFranchiseEntityMapper franchiseEntityMapper;

    @Override
    public Flux<Franchise> getAllFranchises() {
        return franchiseRepository.findAll().map(franchiseEntity -> franchiseEntityMapper.toFranchise(franchiseEntity));
    }

    @Override
    public Mono<Franchise> getById(Long id) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ExceptionResponseEnum.FRANCHISE_NOT_FOUND.getMessage())))
                .map(franchiseEntity -> franchiseEntityMapper.toFranchise(franchiseEntity));
    }

    @Override
    public Mono<Void> saveFranchise(Franchise franchise) {
        return franchiseRepository.save(franchiseEntityMapper.toEntity(franchise)).then();
    }
}
