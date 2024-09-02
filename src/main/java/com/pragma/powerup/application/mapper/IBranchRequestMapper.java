package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.request.ProductRequest;
import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBranchRequestMapper {
    Branch toBranch(BranchRequest branchRequest);
}
