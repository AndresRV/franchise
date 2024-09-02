package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.BranchRequest;
import com.pragma.powerup.application.dto.request.FranchiseRequest;
import com.pragma.powerup.domain.model.Branch;
import com.pragma.powerup.domain.model.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IFranchiseRequestMapper {
    Franchise toFranchise(FranchiseRequest franchiseRequest);
}
