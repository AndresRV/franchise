package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.BranchesWithProductsMaxStockResponse;
import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBranchesWithProductsMaxStockResponseMapper {
    @Mapping(target = "branchName", source = "branch.name")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productStock", source = "product.stock")
    BranchesWithProductsMaxStockResponse toResponse(Branch branch, Product product);
}
