package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.request.FranchiseRequest;
import com.pragma.powerup.application.dto.response.BranchResponse;
import com.pragma.powerup.application.dto.response.FranchiseResponse;
import com.pragma.powerup.application.handler.IBranchHandler;
import com.pragma.powerup.application.handler.IFranchiseHandler;
import com.pragma.powerup.application.mapper.IFranchiseRequestMapper;
import com.pragma.powerup.application.mapper.IFranchiseResponseMapper;
import com.pragma.powerup.application.mapper.IProductRequestMapper;
import com.pragma.powerup.application.mapper.IProductResponseMapper;
import com.pragma.powerup.domain.api.IBranchServicePort;
import com.pragma.powerup.domain.api.IFranchiseServicePort;
import com.pragma.powerup.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class FranchiseHandler implements IFranchiseHandler {
    private final IFranchiseServicePort franchiseServicePort;
    private final IFranchiseResponseMapper franchiseResponseMapper;
    private final IFranchiseRequestMapper franchiseRequestMapper;

    @Override
    public Flux<FranchiseResponse> getAllFranchises() {
        return franchiseServicePort.getAllFranchises().map(franchise -> franchiseResponseMapper.toResponse(franchise));
    }

    @Override
    public Mono<Void> addFranchise(FranchiseRequest franchiseRequest) {
        return franchiseServicePort.addFranchise(franchiseRequestMapper.toFranchise(franchiseRequest));
    }

    @Override
    public Mono<Void> updateFranchiseName(Long id, FranchiseRequest franchiseRequest) {
        return franchiseServicePort.getById(id)
                .flatMap(existingFranchise -> {
                    existingFranchise.setName(franchiseRequest.getName() != null ? franchiseRequest.getName() : existingFranchise.getName());
                    return franchiseServicePort.updateFranchise(existingFranchise);
                });
    }
}
