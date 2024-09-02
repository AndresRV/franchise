package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.request.ProductRequest;
import com.pragma.powerup.application.dto.response.BranchResponse;
import com.pragma.powerup.application.dto.response.ProductResponse;
import com.pragma.powerup.application.handler.IBranchHandler;
import com.pragma.powerup.application.handler.IProductHandler;
import com.pragma.powerup.application.mapper.IBranchRequestMapper;
import com.pragma.powerup.application.mapper.IBranchResponseMapper;
import com.pragma.powerup.application.mapper.IProductRequestMapper;
import com.pragma.powerup.application.mapper.IProductResponseMapper;
import com.pragma.powerup.domain.api.IBranchServicePort;
import com.pragma.powerup.domain.api.IProductServicePort;
import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchHandler implements IBranchHandler {
    private final IBranchServicePort branchServicePort;
    private final IBranchResponseMapper branchResponseMapper;
    private final IBranchRequestMapper branchRequestMapper;

    @Override
    public Flux<BranchResponse> getAllBranches() {
        return branchServicePort.getAllBranches().map(branch -> branchResponseMapper.toResponse(branch));
    }

    @Override
    public Mono<Void> addBranchToFranchise(Long franchiseId, BranchRequest branchRequest) {
        Branch branch = branchRequestMapper.toBranch(branchRequest);
        branch.setFranchiseId(franchiseId);
        return branchServicePort.addBranch(branch);
    }

    @Override
    public Mono<Void> updateBranchName(Long id, BranchRequest branchRequest) {
        return branchServicePort.getById(id)
                .flatMap(existingBranch -> {
                    existingBranch.setName(branchRequest.getName() != null ? branchRequest.getName() : existingBranch.getName());
                    return branchServicePort.updateBranch(existingBranch);
                });
    }
}
