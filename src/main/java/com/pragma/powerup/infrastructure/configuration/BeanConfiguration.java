package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IBranchServicePort;
import com.pragma.powerup.domain.api.IFranchiseServicePort;
import com.pragma.powerup.domain.api.IProductServicePort;
import com.pragma.powerup.domain.spi.IBranchPersistencePort;
import com.pragma.powerup.domain.spi.IFranchisePersistencePort;
import com.pragma.powerup.domain.spi.IProductPersistencePort;
import com.pragma.powerup.domain.usecase.BranchUseCase;
import com.pragma.powerup.domain.usecase.FranchiseUseCase;
import com.pragma.powerup.domain.usecase.ProductUseCase;
import com.pragma.powerup.infrastructure.out.r2dbc.adapter.BranchAdapter;
import com.pragma.powerup.infrastructure.out.r2dbc.adapter.FranchiseAdapter;
import com.pragma.powerup.infrastructure.out.r2dbc.adapter.ProductAdapter;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IBranchEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IFranchiseEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.mapper.IProductEntityMapper;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IBranchRepository;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IFranchiseRepository;
import com.pragma.powerup.infrastructure.out.r2dbc.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    private final IBranchRepository branchRepository;
    private final IBranchEntityMapper branchEntityMapper;

    private final IFranchiseRepository franchiseRepository;
    private final IFranchiseEntityMapper franchiseEntityMapper;

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort());
    }

    @Bean
    public IBranchPersistencePort branchPersistencePort() {
        return new BranchAdapter(branchRepository, branchEntityMapper);
    }

    @Bean
    public IBranchServicePort branchServicePort() {
        return new BranchUseCase(branchPersistencePort());
    }

    @Bean
    public IFranchisePersistencePort franchisePersistencePort() {
        return new FranchiseAdapter(franchiseRepository, franchiseEntityMapper);
    }

    @Bean
    public IFranchiseServicePort franchiseServicePort() {
        return new FranchiseUseCase(franchisePersistencePort());
    }
}
