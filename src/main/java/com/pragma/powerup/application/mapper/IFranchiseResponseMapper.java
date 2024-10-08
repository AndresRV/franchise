package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.FranchiseResponse;
import com.pragma.powerup.domain.model.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IFranchiseResponseMapper {
    FranchiseResponse toResponse(Franchise franchise);
}
