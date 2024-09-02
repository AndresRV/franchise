package com.pragma.powerup.infrastructure.out.r2dbc.mapper;

import com.pragma.powerup.domain.model.Franchise;
import com.pragma.powerup.infrastructure.out.r2dbc.entity.FranchiseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IFranchiseEntityMapper {
    FranchiseEntity toEntity(Franchise franchise);
    Franchise toFranchise(FranchiseEntity franchiseEntity);
}
