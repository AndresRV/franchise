package com.pragma.powerup.infrastructure.out.r2dbc.repository;

import com.pragma.powerup.infrastructure.out.r2dbc.entity.BranchEntity;
import com.pragma.powerup.infrastructure.out.r2dbc.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IFranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
}
