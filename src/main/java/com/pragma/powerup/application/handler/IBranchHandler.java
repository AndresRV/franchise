package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.response.BranchResponse;
import com.pragma.powerup.application.dto.response.BranchesWithProductsMaxStockResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBranchHandler {
    Flux<BranchResponse> getAllBranches();
    Mono<Void> addBranchToFranchise(Long franchiseId, BranchRequest branchRequest);
    Flux<BranchesWithProductsMaxStockResponse> getProductsWithMaxStockByFranchise(Long franchiseId);
    Mono<Void> updateBranchName(Long id, BranchRequest branchRequest);
}
