package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.FranchiseRequest;
import com.pragma.powerup.application.dto.response.FranchiseResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFranchiseHandler {
    Flux<FranchiseResponse> getAllFranchises();
    Mono<Void> addFranchise(FranchiseRequest franchiseRequest);
    Mono<Void> updateFranchiseName(Long id, FranchiseRequest franchiseRequest);
}
