package com.pragma.powerup.infrastructure.out.r2dbc.mapper;

import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Product;
import com.pragma.powerup.infrastructure.out.r2dbc.entity.BranchEntity;
import com.pragma.powerup.infrastructure.out.r2dbc.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBranchEntityMapper {
    BranchEntity toEntity(Branch branch);
    Branch toBranch(BranchEntity branchEntity);
}
